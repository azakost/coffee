package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeAutoCheckAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeAutoCheck {
    public static boolean sendAutoCheck(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 10);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeAutoCheckAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
