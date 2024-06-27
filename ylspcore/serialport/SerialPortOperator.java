package com.levending.ylspcore.serialport;

import android.os.SystemClock;
import android_serialport_api.SerialPort;
import com.levending.ylspcore.common.SPCoreConvertUtils;
import com.levending.ylspcore.common.SPCoreLogUtil;
import java.io.File;

public class SerialPortOperator {
    ISerialPortListener iSerialPortListener = null;
    boolean openState = false;
    SerialPort serialPort = null;
    public String spoName = "spoName";
    Thread threadReadData = null;

    public void initSerialPort(String str, int i, int i2) {
        SPCoreLogUtil.println(this.spoName + "->initSerialPort() 准备初始化串口 serialPort:" + this.serialPort);
        if (this.serialPort == null) {
            this.serialPort = new SerialPort(new File(str), i, i2);
            SPCoreLogUtil.println(this.spoName + "->initSerialPort() 初始化串口");
            return;
        }
        SPCoreLogUtil.println(this.spoName + "->initSerialPort() 串口已经被初始化");
    }

    public void setListener(ISerialPortListener iSerialPortListener2) {
        this.iSerialPortListener = iSerialPortListener2;
    }

    public void removeListener() {
        this.iSerialPortListener = null;
    }

    public boolean openSerialPort() {
        SPCoreLogUtil.println("SerialPortOperator-->openSerialPort() 准备打开串口");
        if (this.openState) {
            SPCoreLogUtil.println(this.spoName + "->openSerialPort() 此串口已经被打开");
        }
        if (this.serialPort == null) {
            SPCoreLogUtil.println(this.spoName + "->openSerialPort() 请先初始化串口");
        }
        SPCoreLogUtil.println(this.spoName + "->执行serialPort.open() serialPort:" + this.serialPort);
        if (this.serialPort != null) {
            this.openState = this.serialPort.open();
            SPCoreLogUtil.println(this.spoName + "->执行serialPort.open() openState:" + this.openState);
            if (this.threadReadData == null && this.openState) {
                this.threadReadData = new Thread(new SerailPortReadRunable(this));
                Thread thread = this.threadReadData;
                thread.setName(this.spoName + "_SerailPortReadRunable");
                this.threadReadData.start();
            }
        }
        return this.openState;
    }

    public void exitReadThread() {
        if (this.threadReadData != null) {
            this.threadReadData.interrupt();
        }
    }

    public void dispose() {
        if (this.threadReadData != null) {
            this.threadReadData = null;
        }
        if (this.serialPort != null) {
            boolean closeStream = this.serialPort.closeStream();
            SPCoreLogUtil.println(this.spoName + "->dispose() closeStream 关闭流结果:" + closeStream);
            this.serialPort.closeSP();
            this.serialPort = null;
            this.openState = false;
            SPCoreLogUtil.println(this.spoName + "->dispose() closeSP");
        }
    }

    public void closeSerialPort() {
        SPCoreLogUtil.println(this.spoName + "->closeSerialPort() 开始终止线程");
        exitReadThread();
    }

    public synchronized boolean sendData(byte[] bArr) {
        boolean z;
        SystemClock.sleep(20);
        z = false;
        if (!(this.serialPort == null || bArr == null)) {
            try {
                this.serialPort.getOutputStream().write(bArr);
                this.serialPort.getOutputStream().flush();
                SPCoreLogUtil.println(this.spoName + "-> 发送数据 sendData(" + SPCoreConvertUtils.bytesToHexString(bArr, bArr.length) + ") 长度: " + bArr.length + " 发送结果:" + true);
                if (this.iSerialPortListener != null) {
                    this.iSerialPortListener.onSendData(bArr);
                }
                z = true;
            } catch (Exception e) {
                if (this.iSerialPortListener != null) {
                    this.iSerialPortListener.onReceiveError(e.toString());
                }
            }
        }
        return z;
    }
}
