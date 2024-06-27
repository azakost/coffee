package com.levending.ylcoffeelib.common;

import com.le.iccardlib.protocol.ICProtocol;
import java.util.ArrayList;

public class CoffeeDataConvertUtils {
    public static int byteToInt(byte b) {
        return b & 255;
    }

    public static String bytesToHexString(byte[] bArr, int i) {
        if (i == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static String bytesToHexString(byte b) {
        return Integer.toHexString(b & 255);
    }

    public static String bytesToHexString(ArrayList<Byte> arrayList) {
        byte[] bArr = new byte[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            bArr[i] = arrayList.get(i).byteValue();
        }
        return bytesToHexString(bArr, bArr.length);
    }

    public static byte[] shortToByteArray(short s) {
        byte[] bArr = new byte[2];
        for (int i = 0; i < 2; i++) {
            bArr[i] = (byte) ((s >>> (((bArr.length - 1) - i) * 8)) & 255);
        }
        return bArr;
    }

    public static int byteToShort(byte[] bArr) {
        return (short) ((bArr[1] & 255) | ((bArr[0] & 255) << 8));
    }

    public static synchronized int getHeight4(byte b) {
        int i;
        synchronized (CoffeeDataConvertUtils.class) {
            i = (b & ICProtocol.cmdCode_F0) >> 4;
        }
        return i;
    }

    public static synchronized int getLow4(byte b) {
        byte b2;
        synchronized (CoffeeDataConvertUtils.class) {
            b2 = b & 15;
        }
        return b2;
    }

    public static synchronized int getBit(byte b, int i) {
        synchronized (CoffeeDataConvertUtils.class) {
            if (i == 0) {
                byte b2 = b & 1;
                return b2;
            } else if (i != 1) {
                return 0;
            } else {
                int i2 = (b >> 1) & 1;
                return i2;
            }
        }
    }

    public static String bytesToString(byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer("");
        int length = bArr.length;
        while (i < length) {
            stringBuffer.append((char) (bArr[i] & 255));
            i++;
        }
        return stringBuffer.toString();
    }

    public static String byteToBit(byte b) {
        return "" + ((byte) ((b >> 7) & 1)) + ((byte) ((b >> 6) & 1)) + ((byte) ((b >> 5) & 1)) + ((byte) ((b >> 4) & 1)) + ((byte) ((b >> 3) & 1)) + ((byte) ((b >> 2) & 1)) + ((byte) ((b >> 1) & 1)) + ((byte) ((b >> 0) & 1));
    }
}
