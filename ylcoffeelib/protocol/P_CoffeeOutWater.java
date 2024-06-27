package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeOutWaterAction;
import com.levending.ylcoffeelib.common.CoffeeDataConvertUtils;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeOutWater {
    public static boolean sendOutWater(SerialPortOperator serialPortOperator, byte b, short s) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 17);
        byte[] shortToByteArray = CoffeeDataConvertUtils.shortToByteArray(s);
        byte[] bArr = {b, shortToByteArray[0], shortToByteArray[1]};
        coffeePackage.setPackageDataLength((byte) bArr.length);
        coffeePackage.setPackageData(bArr);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static boolean sendOutWater(SerialPortOperator serialPortOperator, byte b, short s, byte b2) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 17);
        byte[] shortToByteArray = CoffeeDataConvertUtils.shortToByteArray(s);
        byte[] bArr = {b, shortToByteArray[0], shortToByteArray[1], b2};
        coffeePackage.setPackageDataLength((byte) bArr.length);
        coffeePackage.setPackageData(bArr);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeOutWaterAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
