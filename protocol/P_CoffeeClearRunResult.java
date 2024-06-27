package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeClearRunResultAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeClearRunResult {
    public static boolean sendClearRunResult(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 6);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeClearRunResultAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
