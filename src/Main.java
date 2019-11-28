import handler.codec.MsgDecoder;
import util.HexStringUtils;
import util.JT808ProtocolUtils;

public class Main {

    public static void main(String [] avg) throws Exception {
        String source = "7E0704015701816050799800030005010042000000000000000301597BC006CBFF6800EB00B400B4191119110102010400000000EB1E000C00B28986045701199044901000060089FFFFFFFF000600C5FFFFFFFF0042000000000000000301597BC006CBFF6800EB00B400B4191119110131010400000001EB1E000C00B28986045701199044901000060089FFFFFFFF000600C5FFFFFFFF0042000000000000000301597BC006CBFF6800EB00B400B4191119110201010400000002EB1E000C00B28986045701199044901000060089FFFFFFFF000600C5FFFFFFFF0042000000000000000301597BC006CBFF6800EB00B400B4191119110231010400000004EB1E000C00B28986045701199044901000060089FFFFFFFF000600C5FFFFFFFF0042000000000000000301597BC006CBFF6800EB00B400B4191119110259010400000005EB1E000C00B28986045701199044901000060089FFFFFFFF000600C5FFFFFFFF587E";
//        byte [] src = new byte[]{  //需要大端
//                /*标识*/
//                0x7e,//1字节 标识
//                /*消息头*/
//                0x07,0x04,// 协议号
//                0x01,0x57,// 属性
//                0x01, (byte) 0x81,0x60,0x50,0x79, (byte) 0x98,//手机号
//                0x00,0x03,//消息流水号
//                0x00,0x05,//消息包总数
//                0x01,0x00, //包序号  16 字节 数据头
//                /*消息体*/
//                0x42,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
//                0x03,0x01,0x59,0x7B, (byte) 0xC0,0x06, (byte) 0xCB, (byte) 0xFF,0x68,0x00, (byte) 0xEB,0x00, (byte) 0xB4,0x00, (byte) 0xB4,0x19,0x11,0x19,0x11,0x01,0x02,0x01,0x04,0x00,0x00,0x00,0x00, (byte) 0xEB,0x1E,0x00,0x0C,0x00, (byte) 0xB2, (byte) 0x89, (byte) 0x86,0x04,0x57,0x01,0x19, (byte) 0x90,0x44, (byte) 0x90,
//                0x10,0x00,0x06,0x00, (byte) 0x89, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,0x00,0x06,0x00, (byte) 0xC5, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,0x00,0x42,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x03,0x01,0x59,0x7B, (byte) 0xC0,0x06, (byte) 0xCB, (byte) 0xFF,0x68,0x00, (byte) 0xEB,0x00, (byte) 0xB4,0x00, (byte) 0xB4,0x19,
//
//        };

        System.out.println("==============start===================");


        byte[] src = HexStringUtils.decodeHex(source.toCharArray());
        for (int i=0;i<src.length;i++){
            System.out.println(String.format("0x%x",src[i]));
        }

        //去掉头尾标识
        if (src[0]!=0x7e  ||  src[src.length-1]!=0x7e){
            System.err.println("标识错误");
            return;
        }

        byte [] bs = new byte[src.length-2];
        System.arraycopy(src,1,bs,0,src.length-2);

        JT808ProtocolUtils protocolUtils = new JT808ProtocolUtils();
        bs = protocolUtils.doEscape4Receive(bs, 0, bs.length);
        MsgDecoder decoder = new MsgDecoder();
        decoder.bytes2PackageData(bs);
    }

}
