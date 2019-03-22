/** This class file was automatically generated by jASN1 (http://www.beanit.com) */
package com.beanit.josistack.internal.presentation.asn1;

import com.beanit.jasn1.ber.BerLength;
import com.beanit.jasn1.ber.BerTag;
import com.beanit.jasn1.ber.ReverseByteArrayOutputStream;
import com.beanit.jasn1.ber.types.BerInteger;
import com.beanit.jasn1.ber.types.BerType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class ModeSelector implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 17);
  private static final long serialVersionUID = 1L;
  public byte[] code = null;
  private BerInteger modeValue = null;

  public ModeSelector() {}

  public ModeSelector(byte[] code) {
    this.code = code;
  }

  public BerInteger getModeValue() {
    return modeValue;
  }

  public void setModeValue(BerInteger modeValue) {
    this.modeValue = modeValue;
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
    codeLength += modeValue.encode(reverseOS, false);
    // write tag: CONTEXT_CLASS, PRIMITIVE, 0
    reverseOS.write(0x80);
    codeLength += 1;

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
    while (subCodeLength < totalLength) {
      subCodeLength += berTag.decode(is);
      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
        modeValue = new BerInteger();
        subCodeLength += modeValue.decode(is, false);
      }
    }
    if (subCodeLength != totalLength) {
      throw new IOException(
          "Length of set does not match length tag, length tag: "
              + totalLength
              + ", actual set length: "
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

    sb.append("{");
    sb.append("\n");
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (modeValue != null) {
      sb.append("modeValue: ").append(modeValue);
    } else {
      sb.append("modeValue: <empty-required-field>");
    }

    sb.append("\n");
    for (int i = 0; i < indentLevel; i++) {
      sb.append("\t");
    }
    sb.append("}");
  }
}
