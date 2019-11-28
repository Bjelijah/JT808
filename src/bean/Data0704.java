package bean;

import struct.ArrayLengthMarker;
import struct.StructClass;
import struct.StructField;

import java.util.Arrays;

@StructClass
public class Data0704 {
    @StructField(order = 0) short dataNum;
    @StructField(order = 1) byte dataType;

    @Override
    public String toString() {
        return "Data0704{" +
                "dataNum=" + dataNum +
                ", dataType=" + dataType +
                '}';
    }

    public short getDataNum() {
        return dataNum;
    }

    public void setDataNum(short dataNum) {
        this.dataNum = dataNum;
    }

    public byte getDataType() {
        return dataType;
    }

    public void setDataType(byte dataType) {
        this.dataType = dataType;
    }




}
