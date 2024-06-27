package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeDownCupAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeDownCup {
    public static boolean sendDownCup(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 18);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeDownCupAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
