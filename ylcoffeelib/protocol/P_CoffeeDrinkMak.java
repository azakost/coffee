package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.actions.CoffeeDrinkMakeAction;
import com.levending.ylcoffeelib.common.CoffeeDataConvertUtils;
import com.levending.ylspcore.serialport.SerialPortOperator;
import java.util.ArrayList;

public class P_CoffeeDrinkMak {
    private static byte configParameter;
    private static byte[] flowCode;
    static ArrayList<Drink> listDrink = new ArrayList<>();
    private static byte sugarTime;

    public static void setSugarTime(byte b) {
        sugarTime = b;
    }

    public static void setConfigParameter(byte b) {
        configParameter = b;
    }

    public static void setFlowCode(short s) {
        flowCode = CoffeeDataConvertUtils.shortToByteArray(s);
    }

    private static class Drink {
        private byte materialCode;
        private byte outMaterialTime;
        private byte[] outWaterYield;
        private byte stirTime;
        private byte waterType;

        private Drink() {
            this.materialCode = 0;
            this.outMaterialTime = 0;
            this.stirTime = 0;
            this.waterType = 0;
        }

        public void setMaterialCode(byte b) {
            this.materialCode = b;
        }

        public void setOutMaterialTime(byte b) {
            this.outMaterialTime = b;
        }

        public void setOutWaterYield(short s) {
            this.outWaterYield = CoffeeDataConvertUtils.shortToByteArray(s);
        }

        public void setStirTime(byte b) {
            this.stirTime = b;
        }

        public void setWaterType(byte b) {
            this.waterType = b;
        }

        /* access modifiers changed from: private */
        public byte[] getSignDrinkBytes() {
            return new byte[]{this.materialCode, this.outMaterialTime, this.outWaterYield[0], this.outWaterYield[1], this.stirTime};
        }

        /* access modifiers changed from: private */
        public byte[] getSignDrinkBytes308E1() {
            return new byte[]{this.materialCode, this.outMaterialTime, this.outWaterYield[0], this.outWaterYield[1], this.stirTime, this.waterType};
        }
    }

    public static void clearListDrink() {
        listDrink.clear();
    }

    public static void addDrink(byte b, byte b2, short s, byte b3, byte b4) {
        Drink drink = new Drink();
        drink.setMaterialCode(b);
        drink.setOutMaterialTime(b2);
        drink.setOutWaterYield(s);
        drink.setStirTime(b3);
        drink.setWaterType(b4);
        listDrink.add(drink);
    }

    public static boolean sendMakeDrink(SerialPortOperator serialPortOperator, boolean z) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(CoffeeProtocol.deviceType);
        coffeePackage.setDeviceAddress((byte) 0);
        coffeePackage.setCmdWord((byte) 2);
        byte[] dataContentBytes = getDataContentBytes(z);
        coffeePackage.setPackageDataLength((byte) dataContentBytes.length);
        coffeePackage.setPackageData(dataContentBytes);
        return serialPortOperator.sendData(coffeePackage.toBytes());
    }

    private static byte[] getDataContentBytes(boolean z) {
        byte[] bArr = new byte[(((z ? 6 : 5) * listDrink.size()) + 4)];
        bArr[0] = sugarTime;
        int i = 0;
        for (int i2 = 0; i2 < listDrink.size(); i2++) {
            byte[] access$100 = z ? listDrink.get(i2).getSignDrinkBytes308E1() : listDrink.get(i2).getSignDrinkBytes();
            int i3 = i + 1;
            bArr[i3] = access$100[0];
            int i4 = i3 + 1;
            bArr[i4] = access$100[1];
            int i5 = i4 + 1;
            bArr[i5] = access$100[2];
            int i6 = i5 + 1;
            bArr[i6] = access$100[3];
            i = i6 + 1;
            bArr[i] = access$100[4];
            if (z) {
                i++;
                bArr[i] = access$100[5];
            }
        }
        int i7 = i + 1;
        bArr[i7] = configParameter;
        int i8 = i7 + 1;
        bArr[i8] = flowCode[0];
        bArr[i8 + 1] = flowCode[1];
        return bArr;
    }

    public static void parsePackage(CoffeePackage coffeePackage) {
        CoffeeDrinkMakeAction.getInstance().setReceiveBackResult(coffeePackage);
    }
}
