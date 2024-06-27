package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeDownCoverAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeDownCover {
    public static boolean sendDownCover(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_19);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeDownCoverAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
