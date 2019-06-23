/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author joseluisbz
 */
public class RulerComboBoxRenderer implements ListCellRenderer {

  protected RulerPanel panel;
  private int lastSelected = -1;

  public RulerComboBoxRenderer() {
    this(new Font("Monospaced", Font.PLAIN, 9));
  }

  public RulerComboBoxRenderer(Font extFont) {
    panel = new RulerPanel(new RulerData(false, false, ""), extFont);
  }

  @SuppressWarnings({"unchecked"})
  @Override
  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
    if (isSelected) {
      this.lastSelected = index;
    }

    panel.setData((RulerData) value);
    if (isSelected) {
      panel.setBackground(list.getSelectionBackground());
      panel.setForeground(list.getSelectionForeground());
    } else {
      panel.setBackground(list.getBackground());
      panel.setForeground(list.getForeground());
    }
    String LAF = UIManager.getLookAndFeel().getName().toLowerCase();

//    //Funciona igual que la genérica
//    if (LAF.contains("metal")) {
//      if (isSelected) {
//        panel.setBackground(new Color(163, 184, 204));
//      } else {
//        panel.setBackground(new Color(238, 238, 238));
//      }
//    }
//    
//    //Funciona igual que la genérica
//    if (LAF.contains("motif")) {
//      if (isSelected) {
//        panel.setBackground(new Color(0, 0, 0));
//      } else {
//        panel.setBackground(new Color(174, 178, 195));
//      }
//    }
    if (LAF.contains("nimbus")) {
      NimbusLookAndFeel laf = new NimbusLookAndFeel();
      if (isSelected) {
        panel.setBackground(laf.getDerivedColor("nimbusSelectionBackground", 0.0f, 0.0f, 0.0f, 0, false));
        //panel.setBackground(laf.getDerivedColor("ComboBox:\"ComboBox.listRenderer\"[Selected].background", 0.0f, 0.0f, 0.0f, 0, false));
        //panel.setBackground(new Color(57, 105, 138));
      } else {
        panel.setBackground(laf.getDerivedColor("nimbusLightBackground", 0.0f, 0.0f, 0.0f, 0, false));
        //panel.setBackground(laf.getDerivedColor("ComboBox:\"ComboBox.listRenderer\".background", 0.0f, 0.0f, 0.0f, 0, false));
        //panel.setBackground(new Color(255, 255, 255));
      }
    }
    return panel;
  }

  public RulerPanel getPanel() {
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
