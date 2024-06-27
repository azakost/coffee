package com.levending.ylcoffeelib.protocol;

import com.levending.ylcoffeelib.common.CoffeeDataConvertUtils;
import java.util.ArrayList;

public class CoffeeDataParse {
    private ICoffeeDataParseListener iCoffeeDataParseListener = null;
    ArrayList<Byte> listData = new ArrayList<>();
    private byte startFirstTag = CoffeeProtocol.deviceType;
    private byte startSecondTag = 0;

    public interface ICoffeeDataParseListener {
        void onParseCheckNumError(String str);

        void onParseCombinationPackageError(String str);

        void onParseListDataError(String str);

        void onParseLossByte(String str);

        void onParsePackageError(String str);

        void onUnKnowProtocol(String str);
    }

    private static class staticCoffeeControlManager {
        /* access modifiers changed from: private */
        public static final CoffeeDataParse instance = new CoffeeDataParse();

        private staticCoffeeControlManager() {
        }
    }

    public static CoffeeDataParse getInstance() {
        return staticCoffeeControlManager.instance;
    }

    public void setListener(ICoffeeDataParseListener iCoffeeDataParseListener2) {
        this.iCoffeeDataParseListener = iCoffeeDataParseListener2;
    }

    public void removeListener() {
        this.iCoffeeDataParseListener = null;
    }

    public void parseData(byte[] bArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.listData.add(Byte.valueOf(bArr[i2]));
        }
        try {
            if (this.listData.size() >= 5) {
                parseListData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.iCoffeeDataParseListener != null) {
                this.iCoffeeDataParseListener.onParseListDataError(e.toString());
            }
        }
    }

    public void parseListData() {
        int size = this.listData.size();
        while (size > 0 && this.listData.size() >= 5) {
            byte byteValue = this.listData.get(0).byteValue();
            byte byteValue2 = this.listData.get(1).byteValue();
            if (byteValue == this.startFirstTag && byteValue2 == this.startSecondTag) {
                byte byteValue3 = this.listData.get(2).byteValue();
                byte byteValue4 = this.listData.get(3).byteValue();
                if (size >= byteValue4 + 5) {
                    try {
                        combinationPackage(byteValue, byteValue2, byteValue3, byteValue4, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (this.iCoffeeDataParseListener != null) {
                            this.iCoffeeDataParseListener.onParseCombinationPackageError(e.toString());
                        }
                    }
                } else {
                    return;
                }
            } else {
                if (this.iCoffeeDataParseListener != null) {
                    this.iCoffeeDataParseListener.onParseLossByte(CoffeeDataConvertUtils.bytesToHexString(this.listData));
                }
                this.listData.remove(0);
            }
            size = this.listData.size();
            if (size < 5) {
                return;
            }
        }
    }

    private void combinationPackage(byte b, byte b2, byte b3, byte b4, int i) {
        CoffeePackage coffeePackage = new CoffeePackage();
        coffeePackage.setDeviceType(b);
        coffeePackage.setDeviceAddress(b2);
        coffeePackage.setCmdWord(b3);
        coffeePackage.setPackageDataLength(b4);
        if (b4 > 0) {
            byte[] bArr = new byte[b4];
            for (int i2 = 0; i2 < b4; i2++) {
                bArr[i2] = this.listData.get(i + i2 + 4).byteValue();
            }
            coffeePackage.setPackageData(bArr);
        } else {
            coffeePackage.setPackageData((byte[]) null);
        }
        coffeePackage.setCheckNum(this.listData.get(b4 + 4).byteValue());
        for (int i3 = 0; i3 < b4 + 5; i3++) {
            this.listData.remove(0);
        }
        try {
            parsePackage(coffeePackage);
        } catch (Exception e) {
            e.printStackTrace();
            if (this.iCoffeeDataParseListener != null) {
                this.iCoffeeDataParseListener.onParsePackageError(e.toString());
            }
        }
    }

    public void parsePackage(CoffeePackage coffeePackage) {
        byte generateCheckNum = coffeePackage.generateCheckNum();
        if (generateCheckNum == coffeePackage.getCheckNum()) {
            switch (coffeePackage.getCmdWord()) {
                case -95:
                    P_CoffeeReadStatus.parsePackage(coffeePackage);
                    return;
                case -94:
                    P_CoffeeDrinkMak.parsePackage(coffeePackage);
                    return;
                case -93:
                    P_CoffeeConfigTemperature.parsePackage(coffeePackage);
                    return;
                case -92:
                    P_CoffeeConfigInfrared.parsePackage(coffeePackage);
                    return;
                case -91:
                    P_CoffeeClearMachine.parsePackage(coffeePackage);
                    return;
                case -90:
                    P_CoffeeClearRunResult.parsePackage(coffeePackage);
                    return;
                case -89:
                    P_CoffeeReadMachineVersion.parsePackage(coffeePackage);
                    return;
                case -88:
                    P_CoffeeRebootLowerMachine.parsePackage(coffeePackage);
                    return;
                case -87:
                    P_CoffeeConfigAutoDoor.parsePackage(coffeePackage);
                    return;
                case -86:
                    P_CoffeeAutoCheck.parsePackage(coffeePackage);
                    return;
                case -85:
                    P_CoffeeAllFault.parsePackage(coffeePackage);
                    return;
                case -84:
                    P_CoffeeSetOptional.parsePackage(coffeePackage);
                    return;
                case -83:
                    P_CoffeeReadOptional.parsePackage(coffeePackage);
                    return;
                case -79:
                    P_CoffeeOutWater.parsePackage(coffeePackage);
                    return;
                case -78:
                    P_CoffeeDownCup.parsePackage(coffeePackage);
                    return;
                case -77:
                    P_CoffeeDownStick.parsePackage(coffeePackage);
                    return;
                case -76:
                    P_CoffeeMoveMouth.parsePackage(coffeePackage);
                    return;
                case -75:
                    P_CoffeeBrewingDevice.parsePackage(coffeePackage);
                    return;
                case -74:
                    P_CoffeeMillBean.parsePackage(coffeePackage);
                    return;
                case -73:
                    P_CoffeeOutPowder.parsePackage(coffeePackage);
                    return;
                case -72:
                    P_CoffeeAutoDoor.parsePackage(coffeePackage);
                    return;
                case -71:
                    P_CoffeeDownCover.parsePackage(coffeePackage);
                    return;
                case -70:
                    P_CoffeeOutColdWater.parsePackage(coffeePackage);
                    return;
                case -69:
                    P_CoffeeCabinetDoor.parsePackage(coffeePackage);
                    return;
                case -68:
                    P_CoffeeOpenGerLamp.parsePackage(coffeePackage);
                    break;
            }
            if (this.iCoffeeDataParseListener != null) {
                this.iCoffeeDataParseListener.onUnKnowProtocol("unknown protocol");
            }
        } else if (this.iCoffeeDataParseListener != null) {
            this.iCoffeeDataParseListener.onParseCheckNumError("receive checkNum:" + CoffeeDataConvertUtils.bytesToHexString(coffeePackage.getCheckNum()) + " after the calculation:" + CoffeeDataConvertUtils.bytesToHexString(generateCheckNum));
        }
    }
}
