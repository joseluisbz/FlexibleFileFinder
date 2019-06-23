/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
/**
 *
 * @author joseluisbz
 */
public class MainClass {

  private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

  private static final String OS = System.getProperty("os.name").toLowerCase();

  public static Boolean isMacOS() {
    return (OS.contains("mac") || OS.contains("darwin"));
  }

  public static Boolean isLinux() {
    return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
  }

  public static Boolean isWindows() {
    return (OS.contains("windows"));
  }
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static void enableOSXFullscreen(Window window) {
    if (MainClass.isMacOS()) {
      try {
        Class util = Class.forName("com.apple.eawt.FullScreenUtilities");
        Class params[] = new Class[]{Window.class, Boolean.TYPE};
        Method method = util.getMethod("setWindowCanFullScreen", params);
        method.invoke(util, window, true);
      } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
        LOGGER.log(Level.INFO, "enableOSXFullscreen.Exception:{0}", e.getMessage());
      }
    }
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static void toggleOSXFullscreen(Window window) {
    try {
      Class application = Class.forName("com.apple.eawt.Application");
      Method getApplication = application.getMethod("getApplication");
      Object instance = getApplication.invoke(application);
      Method method = application.getMethod("requestToggleFullScreen", Window.class);
      method.invoke(instance, window);
    } catch (ClassNotFoundException | NoSuchMethodException
        | SecurityException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException exp) {
      exp.printStackTrace(System.err);
    }
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    
    try {

      if (isMacOS()) {
        System.setProperty("apple.awt.brushMetalLook", "true");
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("apple.awt.application.name", "FlexibleFileFinder");
        System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
      }
      
      System.out.println("OS:" + OS);
      if (isMacOS()) {
        UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
      } else if (isLinux()) {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
      } else if (isWindows()) {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      } else {
        UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
      }
    } catch (ClassNotFoundException | InstantiationException
        | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
      LOGGER.log(Level.SEVERE, null, ex);
    }
    
    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        RecursiveFolderAndFiles recursiveFolderAndFiles = new RecursiveFolderAndFiles();
        enableOSXFullscreen(recursiveFolderAndFiles);
        toggleOSXFullscreen(recursiveFolderAndFiles);
        recursiveFolderAndFiles.setVisible(true);
      }
    });
  }
}
