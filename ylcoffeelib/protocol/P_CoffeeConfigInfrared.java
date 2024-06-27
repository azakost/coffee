package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeConfigInfraredAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeConfigInfrared {
    public static boolean sendConfigHaveInfrared(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 4);
        coffeePackage.setPackageDataLength((byte) 1);
        coffeePackage.setPackageData(new byte[]{0});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static boolean sendConfigNoInfrared(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 4);
        coffeePackage.setPackageDataLength((byte) 1);
        coffeePackage.setPackageData(new byte[]{1});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeConfigInfraredAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
