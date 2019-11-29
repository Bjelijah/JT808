package bean;

import struct.StructClass;
import struct.StructField;
import util.ParseUtil;

import javax.xml.ws.ServiceMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@StructClass
public class Data0200 {
    @StructField(order = 0) int alarmFlag;
    @StructField(order = 1) int status;
    @StructField(order = 2) int latitude;
    @StructField(order = 3) int longitude;
    @StructField(order = 4) short height;
    @StructField(order = 5) short speed;
    @StructField(order = 6) short direction;
    @StructField(order = 7) byte [] time = new byte[6];




    public void setDirection(short direction) {
        this.direction = direction;
    }
    public int getAlarmFlag() {
        return alarmFlag;
    }

    public void setAlarmFlag(int alarmFlag) {
        this.alarmFlag = alarmFlag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getHeight() {
        return height;
    }



    public int getSpeed() {
        return speed;
    }



    public int getDirection() {
        return direction;
    }



    public void setHeight(short height) {
        this.height = height;
    }



    public void setSpeed(short speed) {
        this.speed = speed;
    }





    public byte[] getTime() {
        return time;
    }

    public void setTime(byte[] time) {
        this.time = time;
    }

    public String getTimeString() throws ParseException {
        String timeStr = ParseUtil.parseBcdStringFromBytes(time,0,time.length);
//        long timeLong = Long.valueOf(timeStr);
//        System.out.println(timeStr);
        Date date = new SimpleDateFormat("yyMMddHHmmss").parse(timeStr);
       return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
//        return date.toString();
    }

    @Override
    public String toString() {
        try {
            return "Data0200{" +
                    "alarmFlag=" + alarmFlag +
                    ", status=" + status +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", height=" + height +
                    ", speed=" + speed +
                    ", direction=" + direction +
                    ", time=" + getTimeString() +
                    '}';
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ";";
    }

}
