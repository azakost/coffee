package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeCabinetDoorAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeCabinetDoor {
    public static boolean sendOpenEleLock(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_1B);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeCabinetDoorAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
