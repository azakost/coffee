package com.levending.ylcoffeelib.protocol;

import java.nio.ByteBuffer;

public class CoffeePackage {
    private byte checkNum = 0;
    private byte cmdWord;
    private byte deviceAddress = 0;
    private byte deviceType = CoffeeProtocol.deviceType;
    private byte[] packageDataContent = null;
    private byte packageDataLength;

    public void setDeviceType(byte b) {
        this.deviceType = b;
    }

    public byte getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceAddress(byte b) {
        this.deviceAddress = b;
    }

    public byte getDeviceAddress() {
        return this.deviceAddress;
    }

    public void setCmdWord(byte b) {
        this.cmdWord = b;
    }

    public byte getCmdWord() {
        return this.cmdWord;
    }

    public void setPackageDataLength(byte b) {
        this.packageDataLength = b;
    }

    public byte getPackageDataLength() {
        return this.packageDataLength;
    }

    public void setPackageData(byte[] bArr) {
        this.packageDataContent = bArr;
    }

    public byte[] getPackageDataContent() {
        return this.packageDataContent;
    }

    public byte[] toBytes() {
        ByteBuffer allocate = ByteBuffer.allocate(this.packageDataLength + 5);
        allocate.put(this.deviceType);
        allocate.put(this.deviceAddress);
        allocate.put(this.cmdWord);
        allocate.put(this.packageDataLength);
        if (this.packageDataContent != null) {
            allocate.put(this.packageDataContent);
        }
        allocate.put(generateCheckNum());
        return allocate.array();
    }

    public byte generateCheckNum() {
        byte b = (byte) (((this.deviceType ^ this.deviceAddress) ^ this.cmdWord) ^ this.packageDataLength);
        if (this.packageDataContent != null) {
            for (byte b2 : this.packageDataContent) {
                b = (byte) (b ^ b2);
            }
        }
        return b;
    }

    public void setCheckNum(byte b) {
        this.checkNum = b;
    }

    public byte getCheckNum() {
        return this.checkNum;
    }

    public boolean checkNumIsOk() {
        return generateCheckNum() == this.checkNum;
    }
}
