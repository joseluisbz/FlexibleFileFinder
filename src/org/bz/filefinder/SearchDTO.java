/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.nio.file.Path;

/**
 *
 * @author joseluisbz@gmail.com
 */
public class SearchDTO {

  private final Integer type;
  private String filename;
  private Exception exception;
  private Path path;
  private String canonicalPath;
  private RulerData[] words;
  public static final int FILENAME = 1;
  public static final int PATH = 2;
  public static final int EXCEPTION = 3;

  public SearchDTO(String filename) {
    this.filename = filename;
    type = FILENAME;
  }

  public SearchDTO(String canonicalPath, Path path, RulerData[] words) {
    this.canonicalPath = canonicalPath;
    this.path = path;
    this.words = words;
    type = PATH;
  }

  public SearchDTO(Exception exception, Path path) {
    this.exception = exception;
    this.path = path;
    type = EXCEPTION;
  }

  public Integer getType() {
    return type;
  }

  public String getFilename() {
    return filename;
  }

  public Exception getException() {
    return exception;
  }

  public Path getPath() {
    return path;
  }

  public String getCanonicalPath() {
    return canonicalPath;
  }

  public RulerData[] getWords() {
    return words;
  }

}
