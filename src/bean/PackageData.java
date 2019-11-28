package bean;

public class PackageData {
    MsgHeader header;
    protected byte[] msgBodyBytes;
    public MsgHeader getMsgHeader() {
        return header;
    }

    public void setMsgHeader(MsgHeader header) {
        this.header = header;
    }

    public MsgHeader getHeader() {
        return header;
    }

    public void setHeader(MsgHeader header) {
        this.header = header;
    }

    public byte[] getMsgBodyBytes() {
        return msgBodyBytes;
    }

    public void setMsgBodyBytes(byte[] msgBodyBytes) {
        this.msgBodyBytes = msgBodyBytes;
    }
}
