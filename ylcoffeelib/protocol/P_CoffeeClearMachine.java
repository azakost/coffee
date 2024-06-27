package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeClearMachineAction;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeClearMachine {
    public static byte channel_1_WaterYield = 100;
    public static byte channel_2_WaterYield = 100;
    public static byte channel_3_WaterYield = 100;
    public static byte channel_4_WaterYield = 100;
    public static byte channel_5_WaterYield = 100;
    public static byte clear_channel = 10;

    public static boolean sendClearV12(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 5);
        byte[] bArr = {clear_channel};
        coffeePackage.setPackageDataLength((byte) bArr.length);
        coffeePackage.setPackageData(bArr);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static boolean sendClearV13(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 5);
        byte[] bArr = {channel_1_WaterYield, channel_2_WaterYield, channel_3_WaterYield, channel_4_WaterYield, channel_5_WaterYield};
        coffeePackage.setPackageDataLength((byte) bArr.length);
        coffeePackage.setPackageData(bArr);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeClearMachineAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
