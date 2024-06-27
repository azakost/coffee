package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeReadMachineVersionAction;
import com.levending.ylcoffeelib.common.CoffeeDataConvertUtils;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeReadMachineVersion {
    public static boolean sendReadMachineVersion(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 7);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeReadMachineVersionAction.getInstance().setCurrentMachineVersion(CoffeeDataConvertUtils.bytesToString(coffeePackage.getPackageDataContent(), 0));
        CoffeeReadMachineVersionAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
