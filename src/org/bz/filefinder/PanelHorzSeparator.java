/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 *
 * @author joseluisbz
 */
public class PanelHorzSeparator extends JPanel {

  private final JPanel jpVert = new JPanel();
  private Color horzBackground = new Color(255, 0, 0, 0 /*0, 64*/);
  private Color vertBackground = new Color(0, 255, 0, 0 /*0, 64*/);

  public PanelHorzSeparator() {
    this(1, 3);
  }

  public PanelHorzSeparator(int SepH, int SepV) {
    super();

    JPanel jpHorz = new JPanel();
    jpHorz.setLayout(new BoxLayout(jpHorz, BoxLayout.LINE_AXIS));
    jpHorz.setBackground(horzBackground);
    jpHorz.add(Box.createRigidArea(new Dimension(SepH, 0)));
    jpHorz.add(new JSeparator(JSeparator.VERTICAL));
    jpHorz.add(Box.createRigidArea(new Dimension(SepH, 0)));

    jpVert.setLayout(new BoxLayout(jpVert, BoxLayout.PAGE_AXIS));
    jpVert.setBackground(vertBackground);
    jpVert.add(Box.createRigidArea(new Dimension(0, SepV)));
    jpVert.add(jpHorz);
    jpVert.add(Box.createRigidArea(new Dimension(0, SepV)));
    jpVert.setMaximumSize(new Dimension(jpHorz.getPreferredSize().width,
        jpVert.getMaximumSize().height));
    init();
  }

  private void init() {
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    setBackground(new Color(0, 0, 0, 0 /*0, 64*/));
    add(jpVert);
    Dimension d = getMinimumSize();
    setSize(d);
    setPreferredSize(d);
    setMaximumSize(new Dimension(d.width, getMaximumSize().height));
    setSize(d);
  }

  public Color getHorzBackground() {
    return horzBackground;
  }

  public void setHorzBackground(Color horzBackground) {
    this.horzBackground = horzBackground;
  }

  public Color getVertBackground() {
    return vertBackground;
  }

  public void setVertBackground(Color vertBackground) {
    this.vertBackground = vertBackground;
  }

}
