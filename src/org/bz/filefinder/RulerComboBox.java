/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author joseluisbz
 */
public class RulerComboBox extends JComboBox {

  private RulerComboBoxRenderer renderer;
  private RulerComboBoxEditor editor;
  private Integer lastValidIndex = 0;

  @SuppressWarnings({"unchecked"})
  public RulerComboBox() {
    this(new Object[0]);
  }

  @SuppressWarnings({"unchecked"})
  public RulerComboBox(Object[] items) {
    super(items);
    renderer = new RulerComboBoxRenderer();
    editor = new RulerComboBoxEditor();
    setRenderer(renderer);
    setEditor(editor);
    addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        JComboBox combo = ((JComboBox) e.getSource());
        if (combo.getSelectedIndex() > -1) {
          lastValidIndex = combo.getSelectedIndex();
        }
        editor.selectAll();
      }
    });
  }

  public Integer getLastValidIndex() {
    return lastValidIndex;
  }

  public void setLastValidIndex(Integer lastValidIndex) {
    this.lastValidIndex = lastValidIndex;
  }

  public int getIndexOf(Object anObject) {
    String stringData = anObject.toString();
    DefaultComboBoxModel model = (DefaultComboBoxModel) getModel();
    for (int i = 0; i < model.getSize(); i++) {
      RulerData item = (RulerData) model.getElementAt(i);
      if (stringData.equals(item.getText())) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public RulerComboBoxRenderer getRenderer() {
    return renderer;
  }

  @Override
  public RulerComboBoxEditor getEditor() {
    return editor;
  }

}
