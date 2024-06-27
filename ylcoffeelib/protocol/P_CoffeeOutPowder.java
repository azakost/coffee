package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeOutPowderAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeOutPowder {
    public static boolean sendOutPowder(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_17);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeOutPowderAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
