package com.levending.ylspcore.serialport;

public interface ISerialPortListener {
    void onReceiveData(byte[] bArr, int i);

    void onReceiveError(String str);

    void onSendData(byte[] bArr);
}
