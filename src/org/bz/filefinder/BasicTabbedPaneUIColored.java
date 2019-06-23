/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.View;
import javax.swing.plaf.basic.BasicGraphicsUtils;

/**
 *
 * @author joseluisbz
 */

public class BasicTabbedPaneUIColored extends BasicTabbedPaneUI {
  private Color background;
  private Color foreground;
  private final JTabbedPane tabbedPane;

  public BasicTabbedPaneUIColored(JTabbedPane tabbedPane, Color background, Color foreground) {
    super();
    this.tabbedPane = tabbedPane;
    this.background = background;
    this.foreground = foreground;
  }

  @Override protected void paintTabBackground(Graphics g, int tabPlacement,
      int tabIndex, int x, int y, int w, int h, boolean isSelected) {
    Color old = tabPane.getBackgroundAt(tabIndex);//g.getColor();
    g.setColor(this.background);
    switch (tabPlacement) {
      case SwingConstants.TOP:
        g.fillRect(x + 1, y + 1, w - 1, h - 1);
        break;
      case SwingConstants.BOTTOM:
        g.fillRect(x, y, w - 1, h - 1);
        break;
      case SwingConstants.LEFT:
        g.fillRect(x + 1, y + 1, w - 1, h - 2);
        break;
      case SwingConstants.RIGHT:
        g.fillRect(x, y + 1, w - 1, h - 2);
        break;
    }
    g.setColor(old);
    //super.paintTabBackground(g, tabPlacement, tabIndex, x, y, w, h, isSelected);

  }

  @Override protected void paintText(Graphics g, int tabPlacement, Font font,
      FontMetrics metrics, int tabIndex, String title, Rectangle textRect,
      boolean isSelected) {
    g.setFont(font);
    View v = getTextViewForTab(tabIndex);
    if (v != null) {
        // html
        v.paint(g, textRect);
    } else {
      // plain text
      int mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);


//      if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
//        g.setColor(this.foreground);
//        SwingUtilities2.drawStringUnderlineCharAt(tabPane, g, title,
//            mnemIndex, textRect.x, textRect.y + metrics.getAscent());
//      } else { // tab disabled
//        g.setColor(tabPane.getBackgroundAt(tabIndex).brighter());
//        SwingUtilities2.drawStringUnderlineCharAt(tabPane, g,title,
//            mnemIndex, textRect.x, textRect.y + metrics.getAscent());
//        g.setColor(tabPane.getBackgroundAt(tabIndex).darker());
//        SwingUtilities2.drawStringUnderlineCharAt(tabPane, g, title,
//            mnemIndex, textRect.x - 1, textRect.y + metrics.getAscent() - 1);
//      }

    if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
      g.setColor(this.foreground);
      BasicGraphicsUtils.drawStringUnderlineCharAt(g, title,
          mnemIndex, textRect.x, textRect.y + metrics.getAscent());
    } else { // tab disabled
      g.setColor(tabPane.getBackgroundAt(tabIndex).brighter());
      BasicGraphicsUtils.drawStringUnderlineCharAt(g,title,
          mnemIndex, textRect.x, textRect.y + metrics.getAscent());
      g.setColor(tabPane.getBackgroundAt(tabIndex).darker());
      BasicGraphicsUtils.drawStringUnderlineCharAt(g, title,
          mnemIndex, textRect.x - 1, textRect.y + metrics.getAscent() - 1);
    }
    }
  }

  public Color getBackground() {
    return background;
  }

  public void setBackground(Color background) {
    this.background = background;
    this.tabbedPane.repaint();
  }

  public Color getForeground() {
    return foreground;
  }

  public void setForeground(Color foreground) {
    this.foreground = foreground;
    this.tabbedPane.repaint();
  }
}
  
