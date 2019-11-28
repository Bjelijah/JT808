package handler.codec;

import bean.*;
import struct.JavaStruct;
import struct.StructException;

import java.util.Arrays;

public class MsgUnpacker {



    public MsgUnpacker(byte [] data) throws StructException {
        int offset = 0;
        JT808Head headObj = new JT808Head();
        JavaStruct.unpack(headObj,data);
        System.out.println(headObj.toString());
        offset += 12;
        boolean hasSub = headObj.isHasSubPackage();
        if (hasSub){
            byte [] subBuf = new byte[2];
            System.arraycopy(data,offset,subBuf,0,2);
            JT808Sub subObj = new JT808Sub();
            JavaStruct.unpack(subObj,subBuf);
            System.out.println(subObj.toString());
            offset += 2;
        }
        int bodyLen = headObj.getBodyLen();
        int protocol = headObj.getMsgId();
        byte [] bodyBuf = new byte[bodyLen];
        System.arraycopy(data,offset,bodyBuf,0,bodyLen);
        msgProtocol(protocol,bodyBuf);


    }

    private void msgProtocol(int protocol,byte [] body) throws StructException {
        switch (protocol){
            case 0x0704:
                handle0704(body);
                break;
        }
    }

    private void handle0704(byte [] body) throws StructException {
        int offset = 0;
        Data0704 dataObj = new Data0704();
        JavaStruct.unpack(dataObj,body);
        System.out.println(dataObj.toString());
        offset += 3;


        int dataNum = dataObj.getDataNum();
        for (int i=0;i<dataNum;i++){
            byte [] reportBuf = new byte[body.length];
            System.arraycopy(body,offset,reportBuf,0,body.length-offset);
            Data0704Report reportObj = new Data0704Report();
            JavaStruct.unpack(reportObj,reportBuf);
            System.out.println(reportObj.toString());

            //解析位置
            byte [] reportDataBuf = reportObj.getReportData();
            Data0200 locationObj = new Data0200();
            JavaStruct.unpack(locationObj,reportDataBuf);
            System.err.println(locationObj.toString());

            offset+=reportObj.getReportDataLen()+2;//word + 66
        }


    }
}
