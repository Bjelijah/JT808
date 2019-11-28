package bean;

import util.ParseUtil;

public class MsgBody {

    byte [] mBuf;


    public MsgBody(int protocol, byte [] buf) {
        mBuf = buf;
        parse(protocol);
    }

    private void parse(int protocol){
        // 0704
        switch (protocol){
            case 0x0704:
                parse0704();
                break;
        }

    }

    private void parse0704(){
        long alarmFlag = ParseUtil.bytesToLong(mBuf,0,false);
        System.out.println("alarmFlag="+alarmFlag);
    }

}
