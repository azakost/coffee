package com.levending.ylcoffeelib.serialport;

import com.levending.ylcoffeelib.common.CoffeeLibLog;
import com.levending.ylcoffeelib.protocol.CoffeeDataParse;
import com.levending.ylspcore.serialport.ISerialPortListener;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class CoffeeSerialPortManager {
    private int baudRate = 38400;
    private String devicePathName = "/dev/ttyO2";
    private int flags = 0;
    private String serialPortName = "CoffeeSerialPort ttyO2";
    public SerialPortOperator spoDriver = new SerialPortOperator();

    private static class staticCoffeeSerialPortManager {
        /* access modifiers changed from: private */
        public static final CoffeeSerialPortManager instance = new CoffeeSerialPortManager();

        private staticCoffeeSerialPortManager() {
        }
    }

    public static CoffeeSerialPortManager getInstance() {
        return staticCoffeeSerialPortManager.instance;
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
            public void onReceiveError(String str) {
            }

            public void onSendData(byte[] bArr) {
            }

            public void onReceiveData(byte[] bArr, int i) {
                CoffeeDataParse.getInstance().parseData(bArr, i);
            }
        });
        return true;
    }

    public boolean openSerialPort() {
        CoffeeLibLog.println("CoffeeSerialPortManager->openSerialPort()");
        return this.spoDriver.openSerialPort();
    }

    public void closeSerialPort() {
        this.spoDriver.closeSerialPort();
    }

    public String toString() {
        return "serialPortName:" + this.serialPortName + " devicePathName:" + this.devicePathName + " baudRate:" + this.baudRate + " flags:" + this.flags;
    }
}
