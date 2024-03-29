/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*

CentOS
java -jar "/media/sf_Users/joseluisbz/Documentos/Java/FlexibleFileFinder/dist/FlexibleFileFinder.jar"
java -jar "/media/sf_Users_Mac/joseluisbz/Documentos/Java/FlexibleFileFinder/dist/FlexibleFileFinder.jar"


macOS Host
java -jar "/Users/joseluisbz/Documentos/Java/FlexibleFileFinder/dist/FlexibleFileFinder.jar"

macOS Guest
java -jar "/Volumes/joseluisbz/Documentos/Java/FlexibleFileFinder/dist/FlexibleFileFinder.jar"

Windows
java -jar "//VBOXSVR/Users_Mac/joseluisbz/Documentos/Java/FlexibleFileFinder/dist/FlexibleFileFinder.jar"

 */
/**
 *
 * @author joseluisbz@gmail.com
 */
public class RecursiveFolderAndFiles extends javax.swing.JFrame {

  private static final Logger LOGGER = Logger.getLogger(RecursiveFolderAndFiles.class.getName());

  /**
   * Creates new form RecursiveFolderAndFiles
   */
  public RecursiveFolderAndFiles() {
    
    System.out.println("JFrame.class.isAssignableFrom(RecursiveFolderAndFiles.class):" +  JFrame.class.isAssignableFrom(RecursiveFolderAndFiles.class));
    System.out.println("RecursiveFolderAndFiles.class.isAssignableFrom(OneSearch.class):" +  RecursiveFolderAndFiles.class.isAssignableFrom(OneSearch.class));
    System.out.println("RulerPanel.class.isAssignableFrom(OneSearch.class):" +  RulerPanel.class.isAssignableFrom(OneSearch.class));
    System.out.println("RulerPanel.class.isAssignableFrom(JPanel.class):" +  RulerPanel.class.isAssignableFrom(JPanel.class));
    System.out.println("JPanel.class.isAssignableFrom(RulerPanel.class):" +  JPanel.class.isAssignableFrom(RulerPanel.class));
    
    
    setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    
    initComponents();
    LOGGER.log(Level.INFO, "System.getProperty(\"user.dir\"): {0}", System.getProperty("user.dir"));
    LOGGER.log(Level.INFO, "System.getProperty(\"user.home\"): {0}", System.getProperty("user.home"));

    OneSearch firstOneSearch = addOneSearch();

    firstOneSearch.setModelUsedFolders(new RulerData[]{
      new RulerData(true, true, firstOneSearch.getCanonicalPath(Paths.get("../../TextTests")))
    });
    firstOneSearch.setModelUsedMatches(new RulerData[]{
      new RulerData(true, true, "*.{txt,old}")
    });
    firstOneSearch.setModelUsedWords(new RulerData[]{
      new RulerData(true, true, "CentOS"),
      new RulerData(true, true, "macOS"),
      new RulerData(true, true, "Windows")
    });

    for (File root : File.listRoots()) {
      firstOneSearch.addFreeFolders(firstOneSearch.getCanonicalPath(root));
      //LOGGER.log(Level.INFO, "root.getCanonicalPath(): {0}", firstOneSearch.getCanonicalPath(root));
    }

    firstOneSearch.addFreeFolders(System.getProperty("user.dir"));
    firstOneSearch.addFreeFolders(System.getProperty("user.home"));

    if (MainClass.isLinux()) {
      firstOneSearch.insertItemUsedFolders(0, new RulerData(true, false, "/mnt"));
      firstOneSearch.addFreeFolders("/mnt");
      firstOneSearch.insertItemUsedFolders(0, new RulerData(true, false, "/media"));
      firstOneSearch.addFreeFolders("/media");
      firstOneSearch.insertItemUsedFolders(0, new RulerData(true, false, "/proc"));
      firstOneSearch.addFreeFolders("/proc");
    }

    if (MainClass.isMacOS()) {
      firstOneSearch.insertItemUsedFolders(0, new RulerData(true, false, "/Volumes"));
      firstOneSearch.addFreeFolders("/Volumes");
    }

    addOneSearch();
  }

  private OneSearch addOneSearch() {
    int idx = tabbedPaneSearch.getTabCount();
    String title = "OneSearch: " + idx + " ";
    OneSearch oneSearch = new OneSearch();
    tabbedPaneSearch.insertTab(title, null, oneSearch, null, idx);
    tabbedPaneSearch.setTabComponentAt(idx, new TabHeader(tabbedPaneSearch, title));
    return oneSearch;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    panelAddSearch = new javax.swing.JPanel();
    labelNameSearch = new javax.swing.JLabel();
    nameSearch = new javax.swing.JTextField();
    buttonAddSearch = new javax.swing.JButton();
    tabbedPaneSearch = new javax.swing.JTabbedPane();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("FlexibleFileFinder");
    setName("FlexibleFileFinder"); // NOI18N

    panelAddSearch.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

    labelNameSearch.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
    labelNameSearch.setText("Name Search:");

    nameSearch.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

    buttonAddSearch.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
    buttonAddSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/bz/filefinder/usedPictures/AddSearch.png"))); // NOI18N
    buttonAddSearch.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonAddSearchActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelAddSearchLayout = new javax.swing.GroupLayout(panelAddSearch);
    panelAddSearch.setLayout(panelAddSearchLayout);
    panelAddSearchLayout.setHorizontalGroup(
      panelAddSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAddSearchLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(labelNameSearch)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(nameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(buttonAddSearch)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    panelAddSearchLayout.setVerticalGroup(
      panelAddSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelAddSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
        .addComponent(labelNameSearch)
        .addComponent(nameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(buttonAddSearch))
    );

    tabbedPaneSearch.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
      .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(tabbedPaneSearch)
          .addGroup(layout.createSequentialGroup()
            .addComponent(panelAddSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 772, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addComponent(panelAddSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tabbedPaneSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void buttonAddSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddSearchActionPerformed
    int idx = tabbedPaneSearch.getTabCount();
    String title;
    if (nameSearch.getText().isEmpty()) {
      title = "OneSearch: " + idx + " ";
    } else {
      title = nameSearch.getText() + ": " + idx + " ";

    }
    tabbedPaneSearch.insertTab(title, null, new OneSearch(), null, idx);
    tabbedPaneSearch.setTabComponentAt(idx, new TabHeader(tabbedPaneSearch, title));
  }//GEN-LAST:event_buttonAddSearchActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonAddSearch;
  private javax.swing.JLabel labelNameSearch;
  private javax.swing.JTextField nameSearch;
  private javax.swing.JPanel panelAddSearch;
  private javax.swing.JTabbedPane tabbedPaneSearch;
  // End of variables declaration//GEN-END:variables
}
