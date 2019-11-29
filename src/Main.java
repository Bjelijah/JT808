import handler.codec.MsgUnpacker;
import util.FileUtil;
import util.HexStringUtils;
import util.JT808ProtocolUtils;

public class Main {




    public static void main(String [] avg) throws Exception {
        String source = "7E0704015701816050799800030005010042000000000000000301597BC006CBFF6800EB00B400B4191119110102010400000000EB1E000C00B28986045701199044901000060089FFFFFFFF000600C5FFFFFFFF0042000000000000000301597BC006CBFF6800EB00B400B4191119110131010400000001EB1E000C00B28986045701199044901000060089FFFFFFFF000600C5FFFFFFFF0042000000000000000301597BC006CBFF6800EB00B400B4191119110201010400000002EB1E000C00B28986045701199044901000060089FFFFFFFF000600C5FFFFFFFF0042000000000000000301597BC006CBFF6800EB00B400B4191119110231010400000004EB1E000C00B28986045701199044901000060089FFFFFFFF000600C5FFFFFFFF0042000000000000000301597BC006CBFF6800EB00B400B4191119110259010400000005EB1E000C00B28986045701199044901000060089FFFFFFFF000600C5FFFFFFFF587E";


        System.out.println("==============start===================");


//        byte[] src = HexStringUtils.decodeHex(source.toCharArray());
        byte[] src = FileUtil.toByteArray("003.txt");
        System.out.println("srcLen="+src.length);
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

        System.err.println("-----------------");
        MsgUnpacker msg = new MsgUnpacker(bs);


    }

}
