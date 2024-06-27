package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeConfigTemperatureAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeConfigTemperature {
    public static boolean sendConfigMaxAndMinWaterTemperature(SerialPortOperator serialPortOperator, byte b, byte b2) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 3);
        byte[] bArr = {b, b2};
        coffeePackage.setPackageData(bArr);
        coffeePackage.setPackageDataLength((byte) bArr.length);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeConfigTemperatureAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
