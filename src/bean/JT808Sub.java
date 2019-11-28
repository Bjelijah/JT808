package bean;

import struct.StructClass;
import struct.StructField;

@StructClass
public class JT808Sub {
    @StructField(order = 0) short totalSubPackage;
    @StructField(order = 1) short seqSubPackage;

    public short getTotalSubPackage() {
        return totalSubPackage;
    }

    public void setTotalSubPackage(short totalSubPackage) {
        this.totalSubPackage = totalSubPackage;
    }

    public short getSeqSubPackage() {
        return seqSubPackage;
    }

    public void setSeqSubPackage(short seqSubPackage) {
        this.seqSubPackage = seqSubPackage;
    }

    @Override
    public String toString() {
        return "JT808Sub{" +
                "totalSubPackage=" + totalSubPackage +
                ", seqSubPackage=" + seqSubPackage +
                '}';
    }
}
