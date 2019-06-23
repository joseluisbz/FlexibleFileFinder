/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author joseluisbz
 */
public class TabHeader extends JPanel {

  private final JPanel leftPanel = new JPanel();
  private final JPanel rightPanel = new JPanel();
  private final JLabel label = new JLabel();
  private final JButton closeButton = new JButton();
  private Icon icon;
  private String text;

  public TabHeader(JTabbedPane tabbedPane, String text) {
    this.text = text;
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
    leftPanel.add(Box.createRigidArea(new Dimension(3, 0)));
    leftPanel.add(label);
    leftPanel.add(Box.createRigidArea(new Dimension(5, 0)));

    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
    rightPanel.add(Box.createRigidArea(new Dimension(5, 0)));
    rightPanel.add(closeButton);
    rightPanel.add(Box.createRigidArea(new Dimension(3, 0)));

    closeButton.setIcon(new ImageIcon(getClass().getResource("/org/bz/filefinder/usedPictures/DelSearch.png")));
    closeButton.setUI(new BasicButtonUI());
    closeButton.setFocusable(true);
    closeButton.setBorderPainted(false);
    closeButton.setBorder(BorderFactory.createEtchedBorder());

    closeButton.addActionListener((ActionEvent e) -> {
      int index = tabbedPane.indexOfTab(text);
      if (index > -1) {
        int action = JOptionPane.showConfirmDialog(null, "Remove '" + text + "'?",
             "Removing Tab", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
        if (JOptionPane.OK_OPTION == action) {
          tabbedPane.remove(index);
        }
      }
    });
    closeButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        Component component = e.getComponent();
        if (component instanceof AbstractButton) {
          ((AbstractButton) component).setBorderPainted(true);
        }
      }

      @Override
      public void mouseExited(MouseEvent e) {
        Component component = e.getComponent();
        if (component instanceof AbstractButton) {
          ((AbstractButton) component).setBorderPainted(false);
        }
      }
    });

    Boolean opaque = false;

    label.setText(text);
    leftPanel.setOpaque(opaque);
    label.setOpaque(opaque);
    rightPanel.setOpaque(opaque);
    closeButton.setOpaque(opaque);
    setOpaque(opaque);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    add(leftPanel, BorderLayout.WEST);
    add(rightPanel, BorderLayout.EAST);
  }

  public Icon getIcon() {
    return icon;
  }

  public void setIcon(Icon icon) {
    this.icon = icon;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
    label.setText(text);
  }

}
