package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeSetOptionalAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeSetOptional {
    public static boolean sendSetOptional(SerialPortOperator serialPortOperator, int i, int i2, boolean z, boolean z2) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 12);
        byte[] bArr = new byte[4];
        bArr[0] = (byte) (i == 1 ? 17 : 18);
        bArr[1] = (byte) (i2 == 1 ? 33 : 34);
        bArr[2] = (byte) (z ? 50 : 49);
        bArr[3] = (byte) (z2 ? 66 : 65);
        coffeePackage.setPackageDataLength((byte) bArr.length);
        coffeePackage.setPackageData(bArr);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeSetOptionalAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
