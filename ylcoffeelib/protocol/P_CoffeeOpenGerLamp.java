package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeOpenGerLampAction;
import com.levending.ylcoffeelib.common.CoffeeDataConvertUtils;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeOpenGerLamp {
    public static boolean sendOpenGerLamp(SerialPortOperator serialPortOperator, int i, float f) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_1C);
        coffeePackage.setPackageDataLength((byte) 3);
        byte[] shortToByteArray = CoffeeDataConvertUtils.shortToByteArray((short) ((int) f));
        coffeePackage.setPackageData(new byte[]{(byte) i, shortToByteArray[0], shortToByteArray[1]});
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeOpenGerLampAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
