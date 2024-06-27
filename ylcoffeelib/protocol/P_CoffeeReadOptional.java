package com.levending.ylcoffeelib.protocol;

import com.igexin.assist.sdk.AssistPushConsts;
import com.igexin.push.config.c;
import com.levending.ylcoffeelib.actions.CoffeeReadOptionalAction;
import com.levending.ylcoffeelib.common.CoffeeDataConvertUtils;
import com.levending.ylspcore.serialport.SerialPortOperator;

public class P_CoffeeReadOptional {
    public static boolean sendReadOptional(SerialPortOperator serialPortOperator) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord(CoffeeProtocol.cmdCode_0D);
        coffeePackage.setPackageDataLength((byte) 0);
        coffeePackage.setPackageData((byte[]) null);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        for (byte bytesToHexString : coffeePackage.getPackageDataContent()) {
            String bytesToHexString2 = CoffeeDataConvertUtils.bytesToHexString(bytesToHexString);
            if (bytesToHexString2.startsWith("1")) {
                CoffeeReadOptionalAction.getInstance().setDoorType(Integer.parseInt(bytesToHexString2.substring(bytesToHexString2.length() - 1)));
            } else if (bytesToHexString2.startsWith(c.G)) {
                CoffeeReadOptionalAction.getInstance().setMouthType(Integer.parseInt(bytesToHexString2.substring(bytesToHexString2.length() - 1)));
            } else if (bytesToHexString2.startsWith(AssistPushConsts.PUSHMESSAGE_ACTION_MULTI_BRAND_RECEIVE_XM)) {
                CoffeeReadOptionalAction.getInstance().setHasGermicidalLamp(bytesToHexString2.endsWith(c.G));
            } else if (bytesToHexString2.startsWith(AssistPushConsts.PUSHMESSAGE_ACTION_MULTI_BRAND_RECEIVE_MZ)) {
                CoffeeReadOptionalAction.getInstance().setHasBeanMarquee(bytesToHexString2.endsWith(c.G));
            }
        }
        CoffeeReadOptionalAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
