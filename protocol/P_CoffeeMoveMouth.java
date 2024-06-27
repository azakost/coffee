package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeMoveMouthAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeMoveMouth {
    public static boolean sendMoveMouthBuiltIn(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_14);
        coffeePackage.setPackageDataLength((byte) 1);
        coffeePackage.setPackageData(new byte[]{1});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static boolean sendMoveMouthBuiltOut(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_14);
        coffeePackage.setPackageDataLength((byte) 1);
        coffeePackage.setPackageData(new byte[]{2});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeMoveMouthAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
