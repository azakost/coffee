package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeAutoDoorAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeAutoDoor {
    public static boolean sendAutoDoorOpen(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_18);
        coffeePackage.setPackageDataLength((byte) 1);
        coffeePackage.setPackageData(new byte[]{2});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static boolean sendAutoDoorClose(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_18);
        coffeePackage.setPackageDataLength((byte) 1);
        coffeePackage.setPackageData(new byte[]{1});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static boolean sendElectDoorOpen(SerialPortOperator serialPortOperator, int i) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_18);
        coffeePackage.setPackageDataLength((byte) 1);
        coffeePackage.setPackageData(new byte[]{(byte) i});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeAutoDoorAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
