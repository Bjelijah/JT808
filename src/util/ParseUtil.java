package util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class ParseUtil {

    private static BitOperator bitOperator =  new BitOperator();
    private static BCD8421Operater bcd8421Operater = new BCD8421Operater();

    public static float parseFloatFromBytes(byte[] data, int startIndex, int length) {
        return parseFloatFromBytes(data, startIndex, length, 0f);
    }

    private static float parseFloatFromBytes(byte[] data, int startIndex, int length, float defaultVal) {
        try {
            // 字节数大于4,从起始索引开始向后处理4个字节,其余超出部分丢弃
            final int len = length > 4 ? 4 : length;
            byte[] tmp = new byte[len];
            System.arraycopy(data, startIndex, tmp, 0, len);
            return bitOperator.byte2Float(tmp);
        } catch (Exception e) {
            System.err.println("解析浮点数出错:{}"+e.getMessage());
            e.printStackTrace();
            return defaultVal;
        }
    }

    public static String parseStringFromBytes(byte[] data, int startIndex, int lenth) {
        return parseStringFromBytes(data, startIndex, lenth, null);
    }

    private static String parseStringFromBytes(byte[] data, int startIndex, int lenth, String defaultVal) {
        try {
            byte[] tmp = new byte[lenth];
            System.arraycopy(data, startIndex, tmp, 0, lenth);
            return new String(tmp, Charset.forName("GBK"));
        } catch (Exception e) {
            System.err.println("解析字符串出错:{}"+e.getMessage());
            e.printStackTrace();
            return defaultVal;
        }
    }

    public static String parseBcdStringFromBytes(byte[] data, int startIndex, int lenth) {
        return parseBcdStringFromBytes(data, startIndex, lenth, null);
    }

    private static String parseBcdStringFromBytes(byte[] data, int startIndex, int lenth, String defaultVal) {
        try {
            byte[] tmp = new byte[lenth];
            System.arraycopy(data, startIndex, tmp, 0, lenth);
            return bcd8421Operater.bcd2String(tmp);
        } catch (Exception e) {
            System.err.println("解析BCD(8421码)出错:{}"+ e.getMessage());
            e.printStackTrace();
            return defaultVal;
        }
    }

    public static int parseIntFromBytes(byte[] data, int startIndex, int length) {
        return parseIntFromBytes(data, startIndex, length, 0);
    }

    private static int parseIntFromBytes(byte[] data, int startIndex, int length, int defaultVal) {
        try {
            // 字节数大于4,从起始索引开始向后处理4个字节,其余超出部分丢弃
            final int len = length > 4 ? 4 : length;
            byte[] tmp = new byte[len];
            System.arraycopy(data, startIndex, tmp, 0, len);
            return bitOperator.byteToInteger(tmp);
        } catch (Exception e) {
            System.err.println("解析整数出错:{} "+ e.getMessage());
            e.printStackTrace();
            return defaultVal;
        }
    }

    public static long bytesToLong(byte[] input, int offset, boolean littleEndian) {
        // 将byte[] 封装为 ByteBuffer
        ByteBuffer buffer = ByteBuffer.wrap(input,offset,8);
        if(littleEndian){
            // ByteBuffer.order(ByteOrder) 方法指定字节序,即大小端模式(BIG_ENDIAN/LITTLE_ENDIAN)
            // ByteBuffer 默认为大端(BIG_ENDIAN)模式
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        return buffer.getLong();
    }

}
