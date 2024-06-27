package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeOutColdWaterAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeOutColdWater {
    public static boolean sendOutColdWater(SerialPortOperator serialPortOperator, int i) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 26);
        coffeePackage.setPackageDataLength((byte) 1);
        coffeePackage.setPackageData(new byte[]{(byte) i});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeOutColdWaterAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
