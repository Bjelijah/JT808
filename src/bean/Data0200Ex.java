package bean;

import struct.ArrayLengthMarker;
import struct.StructClass;
import struct.StructField;

import java.util.Arrays;

@StructClass
public class Data0200Ex {
    @StructField(order = 0) byte exId;
    @StructField(order = 1)
    @ArrayLengthMarker(fieldName = "exData")
    byte exLen;
    @StructField(order = 2) byte [] exData;

    @Override
    public String toString() {
        return "Data0200Ex{" +
                "exId=" + String.format("0x%x",exId) +
                ", exLen=" + exLen +
                ", exData=" + Arrays.toString(exData) +
                '}';
    }

    public byte getExId() {
        return exId;
    }

    public void setExId(byte exId) {
        this.exId = exId;
    }

    public byte getExLen() {
        return exLen;
    }

    public void setExLen(byte exLen) {
        this.exLen = exLen;
    }

    public byte[] getExData() {
        return exData;
    }

    public void setExData(byte[] exData) {
        this.exData = exData;
    }
}
