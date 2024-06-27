package com.levending.ylcoffeelib.serialport;

import com.levending.ylspcore.serialport.ISerialPortListener;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class WatchDogSerialPortManager {
    private int baudRate = 9600;
    private String devicePathName = "/dev/ttyO4";
    private int flags = 0;
    private String serialPortName = "IceSerialPort ttyO4";
    public SerialPortOperator spoDriver = new SerialPortOperator();

    private static class staticIceSerialPortManager {
        /* access modifiers changed from: private */
        public static final WatchDogSerialPortManager instance = new WatchDogSerialPortManager();

        private staticIceSerialPortManager() {
        }
    }

    public static WatchDogSerialPortManager getInstance() {
        return staticIceSerialPortManager.instance;
    }

    public void configSerialPort(String str, String str2, int i, int i2) {
        this.serialPortName = str;
        this.devicePathName = str2;
        this.baudRate = i;
        this.flags = i2;
    }

    public boolean initSerialPort() {
        this.spoDriver.spoName = this.serialPortName;
        this.spoDriver.initSerialPort(this.devicePathName, this.baudRate, this.flags);
        this.spoDriver.setListener(new ISerialPortListener() {
            public void onReceiveData(byte[] bArr, int i) {
            }

            public void onReceiveError(String str) {
            }

            public void onSendData(byte[] bArr) {
            }
        });
        return true;
    }

    public boolean openSerialPort() {
        return this.spoDriver.openSerialPort();
    }

    public void closeSerialPort() {
        this.spoDriver.closeSerialPort();
    }

    public String toString() {
        return "serialPortName:" + this.serialPortName + " devicePathName:" + this.devicePathName + " baudRate:" + this.baudRate + " flags:" + this.flags;
    }
}
