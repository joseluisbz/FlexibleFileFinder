/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bz.filefinder;

/**
 *
 * @author joseluisbz
 */
public class RulerData {

  private Boolean enabled = false;
  private Boolean defaultOperation = false;
  private String text = "";

  public RulerData() {
    this("");
  }

  public RulerData(String text) {
    this(false, text);
  }

  public RulerData(Boolean defaultOperation, String text) {
    this(false, defaultOperation, text);
  }

  public RulerData(Boolean enabled, Boolean defaultOperation, String text) {
    this.enabled = enabled;
    this.defaultOperation = defaultOperation;
    this.text = text;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Boolean getDefaultOperation() {
    return defaultOperation;
  }

  public void setDefaultOperation(Boolean defaultOperation) {
    this.defaultOperation = defaultOperation;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "RulerData{" + "enabled=" + enabled + ", defaultOperation=" + defaultOperation + ", text=" + text + '}';
  }

}
