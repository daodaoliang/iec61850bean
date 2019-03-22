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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WriteRequest implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
  private static final long serialVersionUID = 1L;
  public byte[] code = null;
  private VariableAccessSpecification variableAccessSpecification = null;
  private ListOfData listOfData = null;
  public WriteRequest() {}

  public WriteRequest(byte[] code) {
    this.code = code;
  }

  public VariableAccessSpecification getVariableAccessSpecification() {
    return variableAccessSpecification;
  }

  public void setVariableAccessSpecification(
      VariableAccessSpecification variableAccessSpecification) {
    this.variableAccessSpecification = variableAccessSpecification;
  }

  public ListOfData getListOfData() {
    return listOfData;
  }

  public void setListOfData(ListOfData listOfData) {
    this.listOfData = listOfData;
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
    codeLength += listOfData.encode(reverseOS, false);
    // write tag: CONTEXT_CLASS, CONSTRUCTED, 0
    reverseOS.write(0xA0);
    codeLength += 1;

    codeLength += variableAccessSpecification.encode(reverseOS);

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
    variableAccessSpecification = new VariableAccessSpecification();
    subCodeLength += variableAccessSpecification.decode(is, berTag);
    subCodeLength += berTag.decode(is);

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
      listOfData = new ListOfData();
      subCodeLength += listOfData.decode(is, false);
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
    sb.append("\n");
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (variableAccessSpecification != null) {
      sb.append("variableAccessSpecification: ");
      variableAccessSpecification.appendAsString(sb, indentLevel + 1);
    } else {
      sb.append("variableAccessSpecification: <empty-required-field>");
    }

    sb.append(",\n");
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (listOfData != null) {
      sb.append("listOfData: ");
      listOfData.appendAsString(sb, indentLevel + 1);
    } else {
      sb.append("listOfData: <empty-required-field>");
    }

    sb.append("\n");
    for (int i = 0; i < indentLevel; i++) {
      sb.append("\t");
    }
    sb.append("}");
  }

  public static class ListOfData implements BerType, Serializable {

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
    private static final long serialVersionUID = 1L;
    public byte[] code = null;
    private List<Data> seqOf = null;

    public ListOfData() {
      seqOf = new ArrayList<Data>();
    }

    public ListOfData(byte[] code) {
      this.code = code;
    }

    public List<Data> getData() {
      if (seqOf == null) {
        seqOf = new ArrayList<Data>();
      }
      return seqOf;
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
      for (int i = (seqOf.size() - 1); i >= 0; i--) {
        codeLength += seqOf.get(i).encode(reverseOS);
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
      if (withTag) {
        codeLength += tag.decodeAndCheck(is);
      }

      BerLength length = new BerLength();
      codeLength += length.decode(is);
      int totalLength = length.val;

      while (subCodeLength < totalLength) {
        Data element = new Data();
        subCodeLength += element.decode(is, null);
        seqOf.add(element);
      }
      if (subCodeLength != totalLength) {
        throw new IOException(
            "Decoded SequenceOf or SetOf has wrong length. Expected "
                + totalLength
                + " but has "
                + subCodeLength);
      }
      codeLength += subCodeLength;

      return codeLength;
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

      sb.append("{\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      if (seqOf == null) {
        sb.append("null");
      } else {
        Iterator<Data> it = seqOf.iterator();
        if (it.hasNext()) {
          it.next().appendAsString(sb, indentLevel + 1);
          while (it.hasNext()) {
            sb.append(",\n");
            for (int i = 0; i < indentLevel + 1; i++) {
              sb.append("\t");
            }
            it.next().appendAsString(sb, indentLevel + 1);
          }
        }
      }

      sb.append("\n");
      for (int i = 0; i < indentLevel; i++) {
        sb.append("\t");
      }
      sb.append("}");
    }
  }
}
