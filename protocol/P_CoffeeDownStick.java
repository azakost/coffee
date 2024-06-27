package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeDownStickAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeDownStick {
    public static boolean sendDownStick(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_13);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeDownStickAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
