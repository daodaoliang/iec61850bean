/** This class file was automatically generated by jASN1 (http://www.beanit.com) */
package com.beanit.openiec61850.internal.mms.asn1;

import com.beanit.jasn1.ber.BerLength;
import com.beanit.jasn1.ber.BerTag;
import com.beanit.jasn1.ber.ReverseByteArrayOutputStream;
import com.beanit.jasn1.ber.types.BerBoolean;
import com.beanit.jasn1.ber.types.BerType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class ReadRequest implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
  private static final long serialVersionUID = 1L;
  public byte[] code = null;
  private BerBoolean specificationWithResult = null;
  private VariableAccessSpecification variableAccessSpecification = null;

  public ReadRequest() {}

  public ReadRequest(byte[] code) {
    this.code = code;
  }

  public BerBoolean getSpecificationWithResult() {
    return specificationWithResult;
  }

  public void setSpecificationWithResult(BerBoolean specificationWithResult) {
    this.specificationWithResult = specificationWithResult;
  }

  public VariableAccessSpecification getVariableAccessSpecification() {
    return variableAccessSpecification;
  }

  public void setVariableAccessSpecification(
      VariableAccessSpecification variableAccessSpecification) {
    this.variableAccessSpecification = variableAccessSpecification;
  }

  public int encode(OutputStream reverseOS) throws IOException {
    return encode(reverseOS, true);
  }

  public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

    if (code != null) {
      for (int i = code.length - 1; i >= 0; i--) {
        reverseOS.write(code[i]);
      }
      if (withTag) {
        return tag.encode(reverseOS) + code.length;
      }
      return code.length;
    }

    int codeLength = 0;
    int sublength;

    sublength = variableAccessSpecification.encode(reverseOS);
    codeLength += sublength;
    codeLength += BerLength.encodeLength(reverseOS, sublength);
    // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
    reverseOS.write(0xA1);
    codeLength += 1;

    if (specificationWithResult != null) {
      codeLength += specificationWithResult.encode(reverseOS, false);
      // write tag: CONTEXT_CLASS, PRIMITIVE, 0
      reverseOS.write(0x80);
      codeLength += 1;
    }

    codeLength += BerLength.encodeLength(reverseOS, codeLength);

    if (withTag) {
      codeLength += tag.encode(reverseOS);
    }

    return codeLength;
  }

  public int decode(InputStream is) throws IOException {
    return decode(is, true);
  }

  public int decode(InputStream is, boolean withTag) throws IOException {
    int codeLength = 0;
    int subCodeLength = 0;
    BerTag berTag = new BerTag();

    if (withTag) {
      codeLength += tag.decodeAndCheck(is);
    }

    BerLength length = new BerLength();
    codeLength += length.decode(is);

    int totalLength = length.val;
    codeLength += totalLength;

    subCodeLength += berTag.decode(is);
    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
      specificationWithResult = new BerBoolean();
      subCodeLength += specificationWithResult.decode(is, false);
      subCodeLength += berTag.decode(is);
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
      subCodeLength += length.decode(is);
      variableAccessSpecification = new VariableAccessSpecification();
      subCodeLength += variableAccessSpecification.decode(is, null);
      if (subCodeLength == totalLength) {
        return codeLength;
      }
    }
    throw new IOException(
        "Unexpected end of sequence, length tag: "
            + totalLength
            + ", actual sequence length: "
            + subCodeLength);
  }

  public void encodeAndSave(int encodingSizeGuess) throws IOException {
    ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
    encode(reverseOS, false);
    code = reverseOS.getArray();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    appendAsString(sb, 0);
    return sb.toString();
  }

  public void appendAsString(StringBuilder sb, int indentLevel) {

    sb.append("{");
    boolean firstSelectedElement = true;
    if (specificationWithResult != null) {
      sb.append("\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      sb.append("specificationWithResult: ").append(specificationWithResult);
      firstSelectedElement = false;
    }

    if (!firstSelectedElement) {
      sb.append(",\n");
    }
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (variableAccessSpecification != null) {
      sb.append("variableAccessSpecification: ");
      variableAccessSpecification.appendAsString(sb, indentLevel + 1);
    } else {
      sb.append("variableAccessSpecification: <empty-required-field>");
    }

    sb.append("\n");
    for (int i = 0; i < indentLevel; i++) {
      sb.append("\t");
    }
    sb.append("}");
  }
}
