package com.levending.ylspcore.common;

import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import com.le.iccardlib.protocol.ICProtocol;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;

public class SPCoreConvertUtils {
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

    public static String bytesToHexString(ArrayList<Byte> arrayList) {
        byte[] bArr = new byte[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            bArr[i] = arrayList.get(i).byteValue();
        }
        return bytesToHexString(bArr, bArr.length);
    }

    public static String bytesToString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String valueOf = String.valueOf((char) b);
            if (!TextUtils.isEmpty(valueOf) && !valueOf.equals("\u0000")) {
                sb.append(valueOf);
            }
        }
        return sb.toString();
    }

    public static String bytesToHex(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    public static String bytesToHex(byte b) {
        String hexString = Integer.toHexString(b & 255);
        if (hexString.length() < 2) {
            hexString = 0 + hexString;
        }
        return hexString.toUpperCase(Locale.US);
    }

    public static String byteToHexString(byte b) {
        return Integer.toHexString(b & 255);
    }

    public static int byteToShort(byte[] bArr) {
        return (short) ((bArr[1] & 255) | ((bArr[0] & 255) << 8));
    }

    public static int bytesToInt(byte[] bArr) {
        return ByteBuffer.wrap(bArr).getInt();
    }

    public static int bytesToIntLittleEndian(byte[] bArr) {
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    public static byte[] getBytesReverse(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr2.length; i++) {
            bArr2[i] = (byte) (bArr[i] ^ -1);
        }
        return bArr2;
    }

    public static byte xor(byte[] bArr) {
        byte b = bArr[0];
        for (int i = 1; i < bArr.length; i++) {
            b = (byte) (b ^ bArr[i]);
        }
        return b;
    }

    public static String byteToBit(byte b) {
        return "" + ((byte) ((b >> 7) & 1)) + ((byte) ((b >> 6) & 1)) + ((byte) ((b >> 5) & 1)) + ((byte) ((b >> 4) & 1)) + ((byte) ((b >> 3) & 1)) + ((byte) ((b >> 2) & 1)) + ((byte) ((b >> 1) & 1)) + ((byte) ((b >> 0) & 1));
    }

    public static byte[] shortToByteArray(short s) {
        byte[] bArr = new byte[2];
        for (int i = 0; i < 2; i++) {
            bArr[i] = (byte) ((s >>> (((bArr.length - 1) - i) * 8)) & 255);
        }
        return bArr;
    }

    public static byte[] intToBytes(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] intToBytesLittleEndian(int i, int i2) {
        ByteBuffer allocate = ByteBuffer.allocate(i2);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(i);
        return allocate.array();
    }

    public static long bytesToLong(byte[] bArr, boolean z) {
        if (bArr == null) {
            throw new IllegalArgumentException("byte array is null!");
        } else if (bArr.length <= 8) {
            long j = 0;
            if (z) {
                for (byte b : bArr) {
                    j = (j << 8) | ((long) (b & 255));
                }
            } else {
                for (int length = bArr.length - 1; length >= 0; length--) {
                    j = (j << 8) | ((long) (bArr[length] & 255));
                }
            }
            return j;
        } else {
            throw new IllegalArgumentException("byte array size > 8 !");
        }
    }

    public static byte[] longToBytes(long j, int i, boolean z) {
        byte[] bArr = new byte[8];
        if (z) {
            for (int length = bArr.length - 1; length >= 0; length--) {
                bArr[length] = (byte) ((int) (j & 255));
                j >>= 8;
            }
        } else {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                bArr[i2] = (byte) ((int) (j & 255));
                j >>= 8;
            }
        }
        return bArr.length <= i ? bArr : Arrays.copyOfRange(bArr, bArr.length - i, bArr.length);
    }

    public static String replaceAllSpace(String str) {
        if (str != null) {
            return Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("");
        }
        return "";
    }

    public static byte bitToByte(String str) {
        int i;
        if (str == null) {
            return 0;
        }
        int length = str.length();
        if (length != 4 && length != 8) {
            return 0;
        }
        if (length != 8) {
            i = Integer.parseInt(str, 2);
        } else if (str.charAt(0) == '0') {
            i = Integer.parseInt(str, 2);
        } else {
            i = Integer.parseInt(str, 2) + InputDeviceCompat.SOURCE_ANY;
        }
        return (byte) i;
    }

    public static synchronized int getHeight4(byte b) {
        int i;
        synchronized (SPCoreConvertUtils.class) {
            i = (b & ICProtocol.cmdCode_F0) >> 4;
        }
        return i;
    }

    public static synchronized int getLow4(byte b) {
        byte b2;
        synchronized (SPCoreConvertUtils.class) {
            b2 = b & 15;
        }
        return b2;
    }

    public static byte[] hexToBytes(String str) {
        byte[] bArr;
        int length = str.length();
        if (length % 2 == 1) {
            length++;
            bArr = new byte[(length / 2)];
            str = "0" + str;
        } else {
            bArr = new byte[(length / 2)];
        }
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 2;
            bArr[i2] = hexToByte(str.substring(i, i3));
            i2++;
            i = i3;
        }
        return bArr;
    }

    public static byte hexToByte(String str) {
        return (byte) Integer.parseInt(str, 16);
    }
}
