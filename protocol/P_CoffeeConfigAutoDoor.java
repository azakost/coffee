package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeConfigAutoDoorAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeConfigAutoDoor {
    public static boolean sendConfigHaveAutoDoor(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 9);
        coffeePackage.setPackageDataLength((byte) 1);
        coffeePackage.setPackageData(new byte[]{0});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static boolean sendConfigNoAutoDoor(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 9);
        coffeePackage.setPackageDataLength((byte) 1);
        coffeePackage.setPackageData(new byte[]{1});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeConfigAutoDoorAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
