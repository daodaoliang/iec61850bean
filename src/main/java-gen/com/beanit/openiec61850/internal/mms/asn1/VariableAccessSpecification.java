/** This class file was automatically generated by jASN1 (http://www.beanit.com) */
package com.beanit.openiec61850.internal.mms.asn1;

import com.beanit.jasn1.ber.BerLength;
import com.beanit.jasn1.ber.BerTag;
import com.beanit.jasn1.ber.ReverseByteArrayOutputStream;
import com.beanit.jasn1.ber.types.BerType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class VariableAccessSpecification implements BerType, Serializable {

  private static final long serialVersionUID = 1L;

  public byte[] code = null;
  private VariableDefs listOfVariable = null;
  private ObjectName variableListName = null;

  public VariableAccessSpecification() {}

  public VariableAccessSpecification(byte[] code) {
    this.code = code;
  }

  public VariableDefs getListOfVariable() {
    return listOfVariable;
  }

  public void setListOfVariable(VariableDefs listOfVariable) {
    this.listOfVariable = listOfVariable;
  }

  public ObjectName getVariableListName() {
    return variableListName;
  }

  public void setVariableListName(ObjectName variableListName) {
    this.variableListName = variableListName;
  }

  public int encode(OutputStream reverseOS) throws IOException {

    if (code != null) {
      for (int i = code.length - 1; i >= 0; i--) {
        reverseOS.write(code[i]);
      }
      return code.length;
    }

    int codeLength = 0;
    int sublength;

    if (variableListName != null) {
      sublength = variableListName.encode(reverseOS);
      codeLength += sublength;
      codeLength += BerLength.encodeLength(reverseOS, sublength);
      // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
      reverseOS.write(0xA1);
      codeLength += 1;
      return codeLength;
    }

    if (listOfVariable != null) {
      codeLength += listOfVariable.encode(reverseOS, false);
      // write tag: CONTEXT_CLASS, CONSTRUCTED, 0
      reverseOS.write(0xA0);
      codeLength += 1;
      return codeLength;
    }

    throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
  }

  public int decode(InputStream is) throws IOException {
    return decode(is, null);
  }

  public int decode(InputStream is, BerTag berTag) throws IOException {

    int codeLength = 0;
    BerTag passedTag = berTag;

    if (berTag == null) {
      berTag = new BerTag();
      codeLength += berTag.decode(is);
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
      listOfVariable = new VariableDefs();
      codeLength += listOfVariable.decode(is, false);
      return codeLength;
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
      codeLength += BerLength.skip(is);
      variableListName = new ObjectName();
      codeLength += variableListName.decode(is, null);
      return codeLength;
    }

    if (passedTag != null) {
      return 0;
    }

    throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
  }

  public void encodeAndSave(int encodingSizeGuess) throws IOException {
    ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
    encode(reverseOS);
    code = reverseOS.getArray();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    appendAsString(sb, 0);
    return sb.toString();
  }

  public void appendAsString(StringBuilder sb, int indentLevel) {

    if (listOfVariable != null) {
      sb.append("listOfVariable: ");
      listOfVariable.appendAsString(sb, indentLevel + 1);
      return;
    }

    if (variableListName != null) {
      sb.append("variableListName: ");
      variableListName.appendAsString(sb, indentLevel + 1);
      return;
    }

    sb.append("<none>");
  }
}
