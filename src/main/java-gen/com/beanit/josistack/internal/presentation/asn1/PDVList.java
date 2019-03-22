/** This class file was automatically generated by jASN1 (http://www.beanit.com) */
package com.beanit.josistack.internal.presentation.asn1;

import com.beanit.jasn1.ber.BerLength;
import com.beanit.jasn1.ber.BerTag;
import com.beanit.jasn1.ber.ReverseByteArrayOutputStream;
import com.beanit.jasn1.ber.types.BerAny;
import com.beanit.jasn1.ber.types.BerBitString;
import com.beanit.jasn1.ber.types.BerOctetString;
import com.beanit.jasn1.ber.types.BerType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class PDVList implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
  private static final long serialVersionUID = 1L;
  public byte[] code = null;
  private TransferSyntaxName transferSyntaxName = null;
  private PresentationContextIdentifier presentationContextIdentifier = null;
  private PresentationDataValues presentationDataValues = null;
  public PDVList() {}

  public PDVList(byte[] code) {
    this.code = code;
  }

  public TransferSyntaxName getTransferSyntaxName() {
    return transferSyntaxName;
  }

  public void setTransferSyntaxName(TransferSyntaxName transferSyntaxName) {
    this.transferSyntaxName = transferSyntaxName;
  }

  public PresentationContextIdentifier getPresentationContextIdentifier() {
    return presentationContextIdentifier;
  }

  public void setPresentationContextIdentifier(
      PresentationContextIdentifier presentationContextIdentifier) {
    this.presentationContextIdentifier = presentationContextIdentifier;
  }

  public PresentationDataValues getPresentationDataValues() {
    return presentationDataValues;
  }

  public void setPresentationDataValues(PresentationDataValues presentationDataValues) {
    this.presentationDataValues = presentationDataValues;
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
    codeLength += presentationDataValues.encode(reverseOS);

    codeLength += presentationContextIdentifier.encode(reverseOS, true);

    if (transferSyntaxName != null) {
      codeLength += transferSyntaxName.encode(reverseOS, true);
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
    if (berTag.equals(TransferSyntaxName.tag)) {
      transferSyntaxName = new TransferSyntaxName();
      subCodeLength += transferSyntaxName.decode(is, false);
      subCodeLength += berTag.decode(is);
    }

    if (berTag.equals(PresentationContextIdentifier.tag)) {
      presentationContextIdentifier = new PresentationContextIdentifier();
      subCodeLength += presentationContextIdentifier.decode(is, false);
      subCodeLength += berTag.decode(is);
    } else {
      throw new IOException("Tag does not match the mandatory sequence element tag.");
    }

    presentationDataValues = new PresentationDataValues();
    subCodeLength += presentationDataValues.decode(is, berTag);
    if (subCodeLength == totalLength) {
      return codeLength;
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
    if (transferSyntaxName != null) {
      sb.append("\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      sb.append("transferSyntaxName: ").append(transferSyntaxName);
      firstSelectedElement = false;
    }

    if (!firstSelectedElement) {
      sb.append(",\n");
    }
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (presentationContextIdentifier != null) {
      sb.append("presentationContextIdentifier: ").append(presentationContextIdentifier);
    } else {
      sb.append("presentationContextIdentifier: <empty-required-field>");
    }

    sb.append(",\n");
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (presentationDataValues != null) {
      sb.append("presentationDataValues: ");
      presentationDataValues.appendAsString(sb, indentLevel + 1);
    } else {
      sb.append("presentationDataValues: <empty-required-field>");
    }

    sb.append("\n");
    for (int i = 0; i < indentLevel; i++) {
      sb.append("\t");
    }
    sb.append("}");
  }

  public static class PresentationDataValues implements BerType, Serializable {

    private static final long serialVersionUID = 1L;

    public byte[] code = null;
    private BerAny singleASN1Type = null;
    private BerOctetString octetAligned = null;
    private BerBitString arbitrary = null;

    public PresentationDataValues() {}

    public PresentationDataValues(byte[] code) {
      this.code = code;
    }

    public BerAny getSingleASN1Type() {
      return singleASN1Type;
    }

    public void setSingleASN1Type(BerAny singleASN1Type) {
      this.singleASN1Type = singleASN1Type;
    }

    public BerOctetString getOctetAligned() {
      return octetAligned;
    }

    public void setOctetAligned(BerOctetString octetAligned) {
      this.octetAligned = octetAligned;
    }

    public BerBitString getArbitrary() {
      return arbitrary;
    }

    public void setArbitrary(BerBitString arbitrary) {
      this.arbitrary = arbitrary;
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

      if (arbitrary != null) {
        codeLength += arbitrary.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 2
        reverseOS.write(0x82);
        codeLength += 1;
        return codeLength;
      }

      if (octetAligned != null) {
        codeLength += octetAligned.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 1
        reverseOS.write(0x81);
        codeLength += 1;
        return codeLength;
      }

      if (singleASN1Type != null) {
        sublength = singleASN1Type.encode(reverseOS);
        codeLength += sublength;
        codeLength += BerLength.encodeLength(reverseOS, sublength);
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
        codeLength += BerLength.skip(is);
        singleASN1Type = new BerAny();
        codeLength += singleASN1Type.decode(is, null);
        return codeLength;
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
        octetAligned = new BerOctetString();
        codeLength += octetAligned.decode(is, false);
        return codeLength;
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
        arbitrary = new BerBitString();
        codeLength += arbitrary.decode(is, false);
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

      if (singleASN1Type != null) {
        sb.append("singleASN1Type: ").append(singleASN1Type);
        return;
      }

      if (octetAligned != null) {
        sb.append("octetAligned: ").append(octetAligned);
        return;
      }

      if (arbitrary != null) {
        sb.append("arbitrary: ").append(arbitrary);
        return;
      }

      sb.append("<none>");
    }
  }
}
