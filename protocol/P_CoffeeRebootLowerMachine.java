package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeRebootLowerMachineAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeRebootLowerMachine {
    public static boolean sendRebootLowerMachine(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 8);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeRebootLowerMachineAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
