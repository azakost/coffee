package com.levending.ylspcore.serialport;

import android.os.SystemClock;
import com.levending.ylspcore.common.SPCoreConvertUtils;
import com.levending.ylspcore.common.SPCoreLogUtil;

class SerailPortReadRunable implements Runnable {
    private byte[] bytesReceived = new byte[512];
    private SerialPortOperator serialPortOperator = null;

    public SerailPortReadRunable(SerialPortOperator serialPortOperator2) {
        this.serialPortOperator = serialPortOperator2;
    }

    public void run() {
        StringBuilder sb;
        SPCoreLogUtil.println(this.serialPortOperator.spoName + "->ThreadRead线程启动");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int available = this.serialPortOperator.serialPort.getInputStream().available();
                if (available > 0) {
                    SPCoreLogUtil.println(this.serialPortOperator.spoName + "->------------准备读取数据------------");
                    int read = this.serialPortOperator.serialPort.getInputStream().read(this.bytesReceived);
                    byte[] bArr = new byte[read];
                    System.arraycopy(this.bytesReceived, 0, bArr, 0, read);
                    SPCoreLogUtil.println(this.serialPortOperator.spoName + "->收到数据大小 readSize: " + available + "  receiveCount: " + read);
                    SPCoreLogUtil.println(this.serialPortOperator.spoName + "->收到原始数据:byte:" + SPCoreConvertUtils.bytesToString(bArr) + "|hex:" + SPCoreConvertUtils.bytesToHexString(bArr, read));
                    reportData(bArr, read);
                }
                SystemClock.sleep(200);
            } catch (Exception e) {
                SPCoreLogUtil.println(this.serialPortOperator.spoName + "->ThreadRead线程(catch):" + e.toString());
                reportError(e.toString());
                sb = new StringBuilder();
            } catch (Throwable th) {
                SPCoreLogUtil.println(this.serialPortOperator.spoName + "->ThreadRead 线程(finally)");
                this.serialPortOperator.dispose();
                throw th;
            }
        }
        SPCoreLogUtil.println(this.serialPortOperator.spoName + "->ThreadRead run() 线程正常退出");
        sb = new StringBuilder();
        sb.append(this.serialPortOperator.spoName);
        sb.append("->ThreadRead 线程(finally)");
        SPCoreLogUtil.println(sb.toString());
        this.serialPortOperator.dispose();
    }

    private void reportData(byte[] bArr, int i) {
        if (this.serialPortOperator.iSerialPortListener != null) {
            this.serialPortOperator.iSerialPortListener.onReceiveData(bArr, i);
        }
    }

    private void reportError(String str) {
        if (this.serialPortOperator.iSerialPortListener != null) {
            this.serialPortOperator.iSerialPortListener.onReceiveError(str);
        }
    }
}
