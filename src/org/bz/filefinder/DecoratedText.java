/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author joseluisbz@gmail.com
 */
public class DecoratedText {

  private String text;
  private Color background;
  private Color foreground;
  private Font font;

  public DecoratedText(String text) {
    this.text = text;
  }

  public DecoratedText(String text, Font font) {
    this.text = text;
    this.font = font;
  }

  public DecoratedText(String text, Color foreground) {
    this.text = text;
    this.foreground = foreground;
  }

  public DecoratedText(String text, Color foreground, Font font) {
    this.text = text;
    this.foreground = foreground;
    this.font = font;
  }

  public DecoratedText(Color background, String text) {
    this.background = background;
    this.text = text;
  }

  public DecoratedText(Color background, String text, Font font) {
    this.background = background;
    this.text = text;
    this.font = font;
  }

  public DecoratedText(Color background, String text, Color foreground) {
    this.background = background;
    this.text = text;
    this.foreground = foreground;
  }

  public DecoratedText(Color background, String text, Color foreground, Font font) {
    this.background = background;
    this.text = text;
    this.foreground = foreground;
    this.font = font;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Color getBackground() {
    return background;
  }

  public void setBackground(Color background) {
    this.background = background;
  }

  public Color getForeground() {
    return foreground;
  }

  public void setForeground(Color foreground) {
    this.foreground = foreground;
  }

  public Font getFont() {
    return font;
  }

  public void setFont(Font font) {
    this.font = font;
  }

  @Override
  public String toString() {
    return "DecoratedText{" + "text=" + text + ", background=" + background
        + ", foreground=" + foreground + ", font=" + font + "}";
  }

  public SimpleAttributeSet getAttributeSet(JTextPane textPane) {
    SimpleAttributeSet attrSet = new SimpleAttributeSet();
    if (getBackground() != null) {
      StyleConstants.setBackground(attrSet, getBackground());
    } else {
      StyleConstants.setBackground(attrSet, textPane.getBackground());
    }
    if (getForeground() != null) {
      StyleConstants.setForeground(attrSet, getForeground());
    } else {
      StyleConstants.setForeground(attrSet, textPane.getForeground());
    }
    Font font;
    if (getFont() != null) {
      font = getFont();
    } else {
      font = textPane.getFont();
    }
    StyleConstants.setFontFamily(attrSet, font.getFamily());
    StyleConstants.setItalic(attrSet, font.isItalic());
    StyleConstants.setBold(attrSet, font.isBold());
    StyleConstants.setFontSize(attrSet, font.getSize());
    return attrSet;
  }

}
