/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Scanner;
import org.bz.filefinder.OneSearch.SwingWorkerSearcher;

/**
 *
 * @author joseluisbz
 */
public class Searcher {

  private volatile boolean runningSearch = false;
  private SwingWorkerSearcher swingWorkerSearcher;

  public Searcher() {
  }

  public boolean isRunningSearch() {
    return runningSearch;
  }

  public void setRunningSearch(boolean runningSearch) {
    this.runningSearch = runningSearch;
  }

  public void setSwingWorkerSearcher(SwingWorkerSearcher swingWorkerSearcher) {
    this.swingWorkerSearcher = swingWorkerSearcher;
  }

  private Boolean fileHasContent(Path path, String word) {
    Scanner scanner = null;
    try {
      scanner = new Scanner(path.toFile(), StandardCharsets.UTF_8.name());
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.contains(word)) {
          return true;
        }
      }
      scanner.close();
    } catch (Exception ex) {
      swingWorkerSearcher.callPublish(new SearchDTO(ex, path));
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
    return false;
  }

  private Boolean isValidMatchesFile(Path path, RulerData[] matches) {
    Boolean isValidMatchesFile = false;
    // Find Include
    if (matches != null) {
      for (RulerData match : matches) {
        FileSystem fs = FileSystems.getDefault();
        if (match != null && !match.getText().isEmpty()) {
          if (match.getDefaultOperation()) {
            PathMatcher pathMatcher = fs.getPathMatcher("glob:" + match.getText());
            if (pathMatcher.matches(path.getFileName())) {
              isValidMatchesFile = true;
            }
          } else {
            PathMatcher pathMatcher = fs.getPathMatcher("glob:" + match.getText());
            if (pathMatcher.matches(path.getFileName())) {
              isValidMatchesFile = false;
              break;
            }
          }
        }
      }
    }
    return isValidMatchesFile;
  }

  private Boolean isValidWordsFile(Path path, RulerData[] words) {
    Boolean isValidWordsFile = true;
    if (words != null) {
      for (RulerData word : words) {
        if (word != null && !word.getText().isEmpty()) {
          if (word.getDefaultOperation()) {
            if (!fileHasContent(path, word.getText())) {
              isValidWordsFile = false;
              break;
            }
          } else {
            if (fileHasContent(path, word.getText())) {
              isValidWordsFile = false;
              break;
            }
          }
        }
      }
    }
    return isValidWordsFile;
  }

  private Boolean isValidFoldersFile(Path path, RulerData[] folders) {
    Boolean isValidFoldersFile = true;
    if (folders != null) {
      for (RulerData folder : folders) {
        if (!folder.getDefaultOperation()) {
          try {
            Path internalPath = Paths.get(folder.getText());
            String canonicalPath = getCanonicalPath(internalPath);
            if (getCanonicalPath(path).equals(canonicalPath)) {
              isValidFoldersFile = false;
              break;
            }
          } catch (Exception ex) {
            isValidFoldersFile = false;
          }
        }
      }
    }
    return isValidFoldersFile;
  }

  private String getCanonicalPath(Path path) {
    try {
      return path.toFile().getCanonicalPath();
    } catch (Exception e) {
      try {
        System.out.println("getCanonicalPath(Path path):" + path);
        return path.normalize().toString();
      } catch (Exception e2) {
        throw e2;
      }
    }
  }

  public void iterativeSearch(RulerData[] folders, RulerData[] matches, RulerData[] words) {
    if (folders != null) {
      for (RulerData folder : folders) {
        if (!runningSearch && swingWorkerSearcher != null) {
          break;
        }

        if ((folder != null) && folder.getDefaultOperation()) {
          Path pathFolder = Paths.get(folder.getText());
          boolean exists = Files.exists(pathFolder, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
          if (exists) {
            try {
              String canonicalFolder = getCanonicalPath(pathFolder);
              DirectoryStream<Path> dirStream = null;
              try {
                dirStream = Files.newDirectoryStream(pathFolder);
                for (Path processPath : dirStream) {
                  if (!runningSearch) {
                    break;
                  }
                  recursiveSearch(canonicalFolder, processPath, folders, matches, words);
                }
                dirStream.close();
              } catch (Exception ex) {
                swingWorkerSearcher.callPublish(new SearchDTO(ex, pathFolder));
              } finally {
                if (dirStream != null) {
                  try {
                    dirStream.close();
                  } catch (IOException ex) {
                    swingWorkerSearcher.callPublish(new SearchDTO(ex, pathFolder));
                  }
                }
              }
            } catch (Exception ex) {
              swingWorkerSearcher.callPublish(new SearchDTO(ex, pathFolder));
            }
          }
        }
      }
    }
  }

  private void recursiveSearch(String canonicalFolder, Path path, RulerData[] folders, RulerData[] matches, RulerData[] words) {
    try {

      if (!runningSearch) {
        return;
      }
      if (Files.isSymbolicLink(path)) {
        return;
      }
      if (path == null) {
        return;
      }

      String name = getCanonicalPath(path);
      swingWorkerSearcher.callPublish(new SearchDTO(name));

      if (path.toFile().isFile()) {
        if (!isValidMatchesFile(path, matches)) {
          return;
        }

        if (!isValidWordsFile(path, words)) {
          return;
        }
        swingWorkerSearcher.callPublish(new SearchDTO(canonicalFolder, path, words));
      } else if (path.toFile().isDirectory()) {
        boolean exists = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        if (!isValidFoldersFile(path, folders) || !exists) {
          return;
        }

        DirectoryStream<Path> dirStream = null;
        try {
          dirStream = Files.newDirectoryStream(path);
          try {
            for (Path processPath : dirStream) {
              recursiveSearch(canonicalFolder, processPath, folders, matches, words);
            }
          } catch (Exception ex) {
            swingWorkerSearcher.callPublish(new SearchDTO(ex, path));
          }
          dirStream.close();
        } catch (Exception ex) {
          swingWorkerSearcher.callPublish(new SearchDTO(ex, path));
        } finally {
          if (dirStream != null) {
            try {
              dirStream.close();
            } catch (IOException ex) {
              swingWorkerSearcher.callPublish(new SearchDTO(ex, path));
            }
          }
        }
      } else {
        //path.toFile()
      }
    } catch (Exception ex) {
      swingWorkerSearcher.callPublish(new SearchDTO(ex, path));
    }
  }

}
