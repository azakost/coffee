package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeAllFaultAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeAllFault {
    public static boolean sendReadAllFault(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 11);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeAllFaultAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
