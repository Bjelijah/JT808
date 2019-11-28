package bean;

import struct.ArrayLengthMarker;
import struct.StructClass;
import struct.StructField;

import java.util.Arrays;

@StructClass
public class Data0704Report {
    @StructField(order = 0) @ArrayLengthMarker(fieldName = "reportData") short reportDataLen;
    @StructField(order = 1) byte [] reportData;

    @Override
    public String toString() {
        return "Data0704Report{" +
                "reportDataLen=" + reportDataLen +
                ", reportData=" + Arrays.toString(reportData) +
                '}';
    }

    public short getReportDataLen() {
        return reportDataLen;
    }

    public void setReportDataLen(short reportDataLen) {
        this.reportDataLen = reportDataLen;
    }

    public byte[] getReportData() {
        return reportData;
    }

    public void setReportData(byte[] reportData) {
        this.reportData = reportData;
    }
}
