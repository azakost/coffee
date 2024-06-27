package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeMillBeanAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeMillBean {
    public static boolean sendMillBean(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_16);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeMillBeanAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
