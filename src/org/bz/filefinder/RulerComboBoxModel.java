/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author joseluisbz
 */
public class RulerComboBoxModel extends DefaultComboBoxModel {

  private Integer lastValidIndex = 0;

  @SuppressWarnings({"unchecked"})
  RulerComboBoxModel() {
    this(new Object[0]);
  }

  @SuppressWarnings({"unchecked"})
  RulerComboBoxModel(final Object items[]) {
    super(items);
  }

  public Integer getLastValidIndex() {
    return lastValidIndex;
  }

  public void setLastValidIndex(Integer lastValidIndex) {
    this.lastValidIndex = lastValidIndex;
  }

  @Override
  public int getIndexOf(Object anObject) {
    int position = -1;
    if (anObject instanceof RulerData) {
      RulerData rulerData = (RulerData) anObject;
      for (int i = 0; i < this.getSize(); i++) {
        RulerData item = (RulerData) this.getElementAt(i);
        if (rulerData.getText().equals(item.getText())) {
          position = i;
        }
      }
    } else if (anObject instanceof String) {
      String stringData = anObject.toString();
      for (int i = 0; i < this.getSize(); i++) {
        RulerData item = (RulerData) this.getElementAt(i);
        if (stringData.equals(item.getText())) {
          position = i;
        }
      }
    } else {
      return super.getIndexOf(anObject);
    }
    return position;
  }

}
