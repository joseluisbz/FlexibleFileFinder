/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author joseluisbz@gmail.com
 */
public class DecoratedTextMutableTreeNode extends DefaultMutableTreeNode {

  DecoratedTextMutableTreeNode(Object userObject, boolean b) {
    super(userObject,  b);
  }

  DecoratedTextMutableTreeNode(Object userObject) {
    super(userObject);
  }

  @Override
  public String toString() {
    if (userObject == null) {
      return "";
    } else if (userObject instanceof DecoratedText) {
      DecoratedText decText = (DecoratedText)userObject;
      return decText.getText();
    } else if (userObject instanceof DecoratedText[]) {
      DecoratedText[] arrayDecText = (DecoratedText[])userObject;
      StringBuilder sb = new StringBuilder();
      for (DecoratedText decText:arrayDecText) {
        sb.append(decText.getText());
      }
      return sb.toString();
    } else {
      return userObject.toString();
    }
    
  }
}
