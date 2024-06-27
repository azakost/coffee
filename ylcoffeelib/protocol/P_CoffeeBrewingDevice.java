package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeBrewingDeviceAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeBrewingDevice {
    public static boolean sendBrewingDeviceUP(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_15);
        coffeePackage.setPackageDataLength((byte) 1);
        coffeePackage.setPackageData(new byte[]{2});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static boolean sendBrewingDeviceDown(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_15);
        coffeePackage.setPackageDataLength((byte) 1);
        coffeePackage.setPackageData(new byte[]{1});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeBrewingDeviceAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
