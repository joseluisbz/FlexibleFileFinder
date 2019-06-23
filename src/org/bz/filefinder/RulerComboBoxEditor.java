/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxEditor;

/**
 *
 * @author joseluisbz
 */
public class RulerComboBoxEditor implements ComboBoxEditor {

  protected RulerPanel panel;

  public RulerComboBoxEditor() {
    this(new Font("Monospaced", Font.PLAIN, 8));
  }

  public RulerComboBoxEditor(Font extFont) {
    panel = new RulerPanel(extFont);
  }

  @Override
  public void addActionListener(ActionListener l) {
    panel.addActionListener(l);
  }

  @Override
  public void removeActionListener(ActionListener l) {
    panel.removeActionListener(l);
  }

  @Override
  public void selectAll() {
    panel.selectAll();
  }

  @Override
  public Object getItem() {
    return panel.getData();
  }

  @Override
  public void setItem(Object anObject) {
    if (anObject != null) {
      panel.setData((RulerData) anObject);
    } else {
      panel.setData(new RulerData());
    }
  }

  @Override
  public Component getEditorComponent() {
    return panel;
  }
  
  public void setFont(Font font) {
    panel.setFont(font);
  }

  public void setBackground(Color bg) {
    panel.setBackground(bg);
  }

  public void setForeground(Color fg) {
    panel.setForeground(fg);
  }

}
