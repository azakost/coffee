package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeReadMachineAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeReadStatus {
    public static boolean sendReadCoffeeStatus(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 1);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeReadMachineAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
