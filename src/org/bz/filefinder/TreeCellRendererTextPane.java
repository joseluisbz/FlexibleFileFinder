/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author joseluisbz@gmail.com
 */
public class TreeCellRendererTextPane extends JTextPane implements TreeCellRenderer {

  private static final Logger LOGGER = Logger.getLogger(TreeCellRendererTextPane.class.getName());

  @Override
  public Component getTreeCellRendererComponent(JTree tree,
      Object value, boolean selected, boolean expanded, boolean leaf,
      int row, boolean hasFocus) {
    if (leaf) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
      Object obj = node.getUserObject();
      if (obj != null) {
        if (obj instanceof DecoratedText || obj instanceof DecoratedText[]) {
          this.setText("");
          StyledDocument sd = this.getStyledDocument();
          if (obj instanceof DecoratedText) {
            DecoratedText decText = (DecoratedText) obj;
            try {
              sd.insertString(sd.getLength(), decText.getText(), decText.getAttributeSet(this));
            } catch (BadLocationException ex) {
              LOGGER.log(Level.INFO, "BadLocationException: {0}", ex.getMessage());
            }
          }
          if (obj instanceof DecoratedText[]) {
            DecoratedText[] arrayDecText = (DecoratedText[]) obj;
            for (DecoratedText decText : arrayDecText) {
              if (decText != null && sd != null) {
                try {
                  sd.insertString(sd.getLength(), decText.getText(), decText.getAttributeSet(this));
                } catch (BadLocationException ex) {
                  LOGGER.log(Level.INFO, "BadLocationException: {0}", ex.getMessage());
                }
              }
            }
          }
        } else {
          return new DefaultTreeCellRenderer().getTreeCellRendererComponent(tree,
              value, leaf, expanded, leaf, row, hasFocus);
        }
      }
    }
    if (!leaf) {
      return new DefaultTreeCellRenderer().getTreeCellRendererComponent(tree,
          value, leaf, expanded, leaf, row, hasFocus);
    }
    return this;
  }
}
