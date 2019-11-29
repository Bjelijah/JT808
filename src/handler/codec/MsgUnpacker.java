package handler.codec;

import bean.*;
import struct.JavaStruct;
import struct.StructException;
import util.ParseUtil;

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
            byte [] subBuf = new byte[4];
            System.arraycopy(data,offset,subBuf,0,4);
            JT808Sub subObj = new JT808Sub();
            JavaStruct.unpack(subObj,subBuf);
            System.out.println(subObj.toString());
            offset += 4;
        }
        int bodyLen = headObj.getBodyLen();
        System.out.println("bodyLen="+bodyLen);
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
            case 0x200:
                handle200(body);
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
            int reportOffset = 0;
            byte [] reportDataBuf = reportObj.getReportData();
            handle200(reportDataBuf);

            offset+=reportObj.getReportDataLen()+2;//word + 66
        }
    }

    /**
     *  8.18 处理 0200
     * @param buf
     * @return
     */
    private Data0200 handle200(byte [] buf) throws StructException {
        //基本位置信息
        Data0200 locationObj = new Data0200();
        JavaStruct.unpack(locationObj,buf);
        System.err.println(locationObj.toString());
        //附加信息
        byte [] exData = new byte[buf.length-28];//附件信息
        System.arraycopy(buf,28,exData,0,exData.length);
        int exOffset = 0;
        int remain = exData.length-exOffset;

        while(remain>0){
            byte [] exContent = new byte[remain];// 1byte id  1byte len   len*Byte content
            System.arraycopy(exData,exOffset,exContent,0,remain);
            Data0200Ex exObj = handle200Ex(exContent);
            int exDataLen = 1+1+exObj.getExLen();
            exOffset+=exDataLen;
            remain = exData.length-exOffset;
        }
        return locationObj;
    }


    /**
     * 处理附加信息
     */
    private Data0200Ex handle200Ex(byte [] buf) throws StructException {
        Data0200Ex exObj = new Data0200Ex();
        JavaStruct.unpack(exObj,buf);
        System.err.println(exObj.toString());
        int id = exObj.getExId();
        switch (id){
            case 0x01://里程表度数
                int data = ParseUtil.parseIntFromBytes(exObj.getExData(),0,exObj.getExData().length);
                System.out.println("里程表读数："+data);
                break;
            case 0xeb://自定义
                break;
        }
        return exObj;
    }

}
