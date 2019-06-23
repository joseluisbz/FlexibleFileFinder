/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author joseluisbz
 */
public class RulerPanel extends JPanel {

  private Font intFont = new Font("Monospaced", Font.PLAIN, 8);
  private int minWidth;
  private int maxWidth;

  private final JPanel jpEnabled = new JPanel();
  private final JCheckBox jtbEnabled = new JCheckBox();

  private final PanelHorzSeparator panelHorzSeparatorOne = new PanelHorzSeparator();

  private final JPanel jpDefaultOperation = new JPanel();
  private final JToggleButton jtbDefaultOperation = new JToggleButton();

  private final PanelHorzSeparator panelHorzSeparatorTwo = new PanelHorzSeparator();

  private final JPanel jpText = new JPanel();
  private final JTextField jtfText = new JTextField("", 0);

  private final JPanel jpFindSearchRuler = new JPanel();
  private RulerData data;

  public RulerPanel(Font extFont) {
    this(new RulerData(), extFont);
  }

  public RulerPanel(RulerData data, Font extFont) {
    super();
    intFont = extFont;
    initEnabled();
    initDefaultOperation();
    initText();
    this.data = data;
    setData(data);

    jpFindSearchRuler.setLayout(new BoxLayout(jpFindSearchRuler, BoxLayout.LINE_AXIS));

    jpFindSearchRuler.add(Box.createRigidArea(new Dimension(2, 0)));
    jpFindSearchRuler.add(jpEnabled);
    jpFindSearchRuler.add(panelHorzSeparatorOne);
    jpFindSearchRuler.add(jpDefaultOperation);

    jpFindSearchRuler.add(panelHorzSeparatorTwo);
    jpFindSearchRuler.add(jpText);
    jpFindSearchRuler.add(Box.createRigidArea(new Dimension(2, 0)));
    init();
  }

  private void init() {

    jtbEnabled.addActionListener(e -> {
      Container c = SwingUtilities.getAncestorOfClass(JComboBox.class, this);
      if (c instanceof RulerComboBox) {
        RulerComboBox combo = (RulerComboBox) c;
        if (combo.getItemCount() > 0) {
          int lastValidIndex = combo.getLastValidIndex();
          RulerData rd = (RulerData) combo.getItemAt(lastValidIndex);
          boolean b = ((JCheckBox) e.getSource()).isSelected();
          rd.setEnabled(b);
          jtbDefaultOperation.setEnabled(b);
          jtfText.setEnabled(b);
        }
      }
    });

    jtbDefaultOperation.addActionListener(e -> {
      Container c = SwingUtilities.getAncestorOfClass(JComboBox.class, this);
      if (c instanceof RulerComboBox) {
        RulerComboBox combo = (RulerComboBox) c;
        if (combo.getItemCount() > 0) {
          int lastValidIndex = combo.getLastValidIndex();
          RulerData rd = (RulerData) combo.getItemAt(lastValidIndex);
          rd.setDefaultOperation(((JToggleButton) e.getSource()).isSelected());
        }
      }
    });
    
    jtfText.addActionListener(e -> {
      Container c = SwingUtilities.getAncestorOfClass(JComboBox.class, this);
      if (c instanceof RulerComboBox) {
        RulerComboBox combo = (RulerComboBox) c;
        if (combo.getItemCount() > 0) {
          int lastValidIndex = combo.getLastValidIndex();
          RulerData rd = (RulerData) combo.getItemAt(lastValidIndex);
          rd.setText(((JTextField) e.getSource()).getText());
        }
      }
    });
    
    setFont(intFont);
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    add(jpFindSearchRuler);
    Dimension d = getMinimumSize();
  }

  private void initEnabled() {
    jpEnabled.setLayout(new BoxLayout(jpEnabled, BoxLayout.LINE_AXIS));
    jtbEnabled.setFont(intFont);
    jpEnabled.add(jtbEnabled);
  }

  private void initDefaultOperation() {
    jpDefaultOperation.setLayout(new BoxLayout(jpDefaultOperation, BoxLayout.LINE_AXIS));
    jtbDefaultOperation.setFont(intFont);
    jtbDefaultOperation.setIcon(new ImageIcon(getClass().getResource("usedPictures/Exclude.png")));
    jtbDefaultOperation.setSelectedIcon(new ImageIcon(getClass().getResource("usedPictures/Include.png")));
    jtbDefaultOperation.setDisabledIcon(new ImageIcon(getClass().getResource("usedPictures/DisabledExclude.png")));
    jtbDefaultOperation.setDisabledSelectedIcon(new ImageIcon(getClass().getResource("usedPictures/DisabledInclude.png")));
    jtbDefaultOperation.setBorderPainted(false);
    jpDefaultOperation.add(jtbDefaultOperation);
  }

  private void initText() {
    jpText.setLayout(new BoxLayout(jpText, BoxLayout.LINE_AXIS));

    jtfText.setFont(intFont);
    minWidth = (new JTextField()).getMinimumSize().width;
    maxWidth = getStringWidth(intFont, jtfText.getText());
    jtfText.setEditable(false);
    jpText.add(jtfText);
  }

  @SuppressWarnings({"unchecked"})
  private <T> List<T> getComboBoxItems(JComboBox comboBox) {
    if (comboBox != null) {
      List<String> list = new ArrayList<>();
      for (int i = 0; i < comboBox.getItemCount(); i++) {
        list.add(comboBox.getItemAt(i).toString());
      }
      return (List<T>) list;//(ArrayList<T>)list; // list; // (List<T>)list;
    }
    return null;
  }

  private Integer positionItemList(java.util.List list, Object object) {
    Integer position = -1;
    Integer last = list.size();
    for (int pos = 0; pos < last; pos++) {
      if (list.get(pos).toString().equals(object.toString())) {
        position = pos;
      }
    }
    return position;
  }

  public RulerData getData() {
    return new RulerData(
        jtbEnabled.isSelected(),
        jtbDefaultOperation.isSelected(),
        jtfText.getText()
    );
  }

  public void setData(RulerData data) {
    if (data != null) {
      jtbEnabled.setSelected(data.getEnabled());
      jtbDefaultOperation.setEnabled(data.getEnabled());
      jtbDefaultOperation.setSelected(data.getDefaultOperation());
      jtfText.setText(data.getText());
    } else {
      jtbEnabled.setSelected(false);
      jtbDefaultOperation.setEnabled(false);
      jtbDefaultOperation.setSelected(false);
      jtfText.setText("");
    }
  }

  private int getStringWidth(Font f, String s) {
    return intGetFontMetrics(f).stringWidth(s);
  }

  private FontMetrics intGetFontMetrics(Font f) {
    return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics().getFontMetrics(f);
  }

  public void selectAll() {
    jtfText.requestFocusInWindow();
    jtfText.selectAll();
  }

  public void addActionListener(ActionListener l) {
    jtfText.addActionListener(l);
    jtbEnabled.addActionListener(l);
    jtbDefaultOperation.addActionListener(l);
  }

  public void removeActionListener(ActionListener l) {
    jtfText.removeActionListener(l);
    jtbEnabled.removeActionListener(l);
    jtbDefaultOperation.removeActionListener(l);
  }

  @Override
  public void setBackground(Color bg) {
    super.setBackground(bg);
    if (panelHorzSeparatorOne != null) {
      panelHorzSeparatorOne.setBackground(bg);
      panelHorzSeparatorOne.setHorzBackground(bg);
      panelHorzSeparatorOne.setVertBackground(bg);
    }
    if (panelHorzSeparatorTwo != null) {
      panelHorzSeparatorTwo.setBackground(bg);
      panelHorzSeparatorTwo.setHorzBackground(bg);
      panelHorzSeparatorTwo.setVertBackground(bg);
    }
    if (jpEnabled != null) {
      jpEnabled.setBackground(bg);
    }
    if (jpDefaultOperation != null) {
      jpDefaultOperation.setBackground(bg);
    }
    if (jpText != null) {
      jpText.setBackground(bg);
    }
    if (jpFindSearchRuler != null) {
      jpFindSearchRuler.setBackground(bg);
    }
  }
  
  @Override
  public void setForeground(Color fg) {
    super.setForeground(fg);
    if (jtfText != null) {
      jtfText.setForeground(fg);
    }
  }
  
  @Override
  public void setFont(Font font) {
    super.setFont(font);
    if (jpText != null) {
      jpText.setFont(font);
    }
    if (jtfText != null) {
      jtfText.setFont(font);
    }
    
  }

}
