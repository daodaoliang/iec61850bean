/** This class file was automatically generated by jASN1 (http://www.beanit.com) */
package com.beanit.josistack.internal.presentation.asn1;

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

public class ContextList implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
  private static final long serialVersionUID = 1L;
  public byte[] code = null;
  private List<SEQUENCE> seqOf = null;
  public ContextList() {
    seqOf = new ArrayList<SEQUENCE>();
  }

  public ContextList(byte[] code) {
    this.code = code;
  }

  public List<SEQUENCE> getSEQUENCE() {
    if (seqOf == null) {
      seqOf = new ArrayList<SEQUENCE>();
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
      codeLength += seqOf.get(i).encode(reverseOS, true);
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
      SEQUENCE element = new SEQUENCE();
      subCodeLength += element.decode(is, true);
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
      Iterator<SEQUENCE> it = seqOf.iterator();
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

  public static class SEQUENCE implements BerType, Serializable {

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
    private static final long serialVersionUID = 1L;
    public byte[] code = null;
    private PresentationContextIdentifier presentationContextIdentifier = null;
    private AbstractSyntaxName abstractSyntaxName = null;
    private TransferSyntaxNameList transferSyntaxNameList = null;
    public SEQUENCE() {}

    public SEQUENCE(byte[] code) {
      this.code = code;
    }

    public PresentationContextIdentifier getPresentationContextIdentifier() {
      return presentationContextIdentifier;
    }

    public void setPresentationContextIdentifier(
        PresentationContextIdentifier presentationContextIdentifier) {
      this.presentationContextIdentifier = presentationContextIdentifier;
    }

    public AbstractSyntaxName getAbstractSyntaxName() {
      return abstractSyntaxName;
    }

    public void setAbstractSyntaxName(AbstractSyntaxName abstractSyntaxName) {
      this.abstractSyntaxName = abstractSyntaxName;
    }

    public TransferSyntaxNameList getTransferSyntaxNameList() {
      return transferSyntaxNameList;
    }

    public void setTransferSyntaxNameList(TransferSyntaxNameList transferSyntaxNameList) {
      this.transferSyntaxNameList = transferSyntaxNameList;
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
      codeLength += transferSyntaxNameList.encode(reverseOS, true);

      codeLength += abstractSyntaxName.encode(reverseOS, true);

      codeLength += presentationContextIdentifier.encode(reverseOS, true);

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
      if (berTag.equals(PresentationContextIdentifier.tag)) {
        presentationContextIdentifier = new PresentationContextIdentifier();
        subCodeLength += presentationContextIdentifier.decode(is, false);
        subCodeLength += berTag.decode(is);
      } else {
        throw new IOException("Tag does not match the mandatory sequence element tag.");
      }

      if (berTag.equals(AbstractSyntaxName.tag)) {
        abstractSyntaxName = new AbstractSyntaxName();
        subCodeLength += abstractSyntaxName.decode(is, false);
        subCodeLength += berTag.decode(is);
      } else {
        throw new IOException("Tag does not match the mandatory sequence element tag.");
      }

      if (berTag.equals(TransferSyntaxNameList.tag)) {
        transferSyntaxNameList = new TransferSyntaxNameList();
        subCodeLength += transferSyntaxNameList.decode(is, false);
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
      if (presentationContextIdentifier != null) {
        sb.append("presentationContextIdentifier: ").append(presentationContextIdentifier);
      } else {
        sb.append("presentationContextIdentifier: <empty-required-field>");
      }

      sb.append(",\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      if (abstractSyntaxName != null) {
        sb.append("abstractSyntaxName: ").append(abstractSyntaxName);
      } else {
        sb.append("abstractSyntaxName: <empty-required-field>");
      }

      sb.append(",\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      if (transferSyntaxNameList != null) {
        sb.append("transferSyntaxNameList: ");
        transferSyntaxNameList.appendAsString(sb, indentLevel + 1);
      } else {
        sb.append("transferSyntaxNameList: <empty-required-field>");
      }

      sb.append("\n");
      for (int i = 0; i < indentLevel; i++) {
        sb.append("\t");
      }
      sb.append("}");
    }

    public static class TransferSyntaxNameList implements BerType, Serializable {

      public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
      private static final long serialVersionUID = 1L;
      public byte[] code = null;
      private List<TransferSyntaxName> seqOf = null;

      public TransferSyntaxNameList() {
        seqOf = new ArrayList<TransferSyntaxName>();
      }

      public TransferSyntaxNameList(byte[] code) {
        this.code = code;
      }

      public List<TransferSyntaxName> getTransferSyntaxName() {
        if (seqOf == null) {
          seqOf = new ArrayList<TransferSyntaxName>();
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
          codeLength += seqOf.get(i).encode(reverseOS, true);
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
          TransferSyntaxName element = new TransferSyntaxName();
          subCodeLength += element.decode(is, true);
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
        ReverseByteArrayOutputStream reverseOS =
            new ReverseByteArrayOutputStream(encodingSizeGuess);
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
          Iterator<TransferSyntaxName> it = seqOf.iterator();
          if (it.hasNext()) {
            sb.append(it.next());
            while (it.hasNext()) {
              sb.append(",\n");
              for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
              }
              sb.append(it.next());
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
}
