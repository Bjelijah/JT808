package bean;

import struct.StructClass;
import struct.StructField;
import util.ParseUtil;

import java.util.Arrays;

@StructClass
public class JT808Head {
    @StructField(order = 0) short msgId;
    @StructField(order = 1) short msgBodyProps;
    @StructField(order = 2) byte[] phone = new byte[6];
    @StructField(order = 3) short flowId;


//    int msgBodyLength;
//    int encryptionType;
//    boolean hasSubPackage;
//    String reservedBit;
    public short getMsgId() {
        return msgId;
    }

    public void setMsgId(short msgId) {
        this.msgId = msgId;
    }

    public String getBodyProps() {
        int msgBodyLength = msgBodyProps & 0x3ff;
        int encryptionType = (msgBodyProps & 0x1c00) >> 10;
        boolean hasSubPackage = ((msgBodyProps & 0x2000) >> 13) == 1;
        String reservedBit = ((msgBodyProps & 0xc000) >> 14) + "";
        return "{ msgBodyLength "+msgBodyLength
                +" encryptionType "+encryptionType
                +" hasSubPackage "+hasSubPackage
                +" reservedBit "+reservedBit
                +"}";

    }

    public short getMsgBodyProps() {
        return msgBodyProps;
    }

    public void setMsgBodyProps(short msgBodyProps) {
        this.msgBodyProps = msgBodyProps;
    }

    public byte[] getPhone() {
        return phone;
    }

    public String getPhoneString() {
        return ParseUtil.parseBcdStringFromBytes(phone,0,phone.length);
    }

    public void setPhone(byte[] phone) {
        this.phone = phone;
    }

    public short getFlowId() {
        return flowId;
    }

    public void setFlowId(short flowId) {
        this.flowId = flowId;
    }

    @Override
    public String toString() {
        return "JT808Head{" +
                "msgId=" + msgId +
                ", msgBodyProps=" + getBodyProps() +
                ", phone=" +  getPhoneString()+
                ", flowId=" + flowId +
                '}';
    }

    public boolean isHasSubPackage(){
        return  ((msgBodyProps & 0x2000) >> 13) == 1;
    }

    public int getBodyLen(){
        return msgBodyProps & 0x3ff;
    }

}
