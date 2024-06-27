package com.levending.ylcoffeelib.bean;

import com.levending.ylcoffeelib.common.CoffeeDataConvertUtils;

public class CoffeeDeviceStatus {
    public byte configParameter;
    public byte currentRunAction;
    public int currentWaterTemperature;
    public byte deviceCode = -1;
    public byte deviceType = -1;
    public byte drinkDoResult;
    public byte errorCode = -1;
    public int errorNum = -1;
    public int errorType = -1;
    public boolean isCabinetOpen;
    public boolean isCupDoorOpen;
    public int isHaveAutoDoor;
    public boolean isHaveCup;
    public int isHaveInfrared;
    public byte maxWaterTemperature;
    public byte minWaterTemperature;
    public int remainingWater;

    public void setErrorCode(byte b) {
        this.errorCode = b;
        this.errorType = CoffeeDataConvertUtils.getHeight4(b);
        this.errorNum = CoffeeDataConvertUtils.getLow4(b);
    }

    public void setConfigParameter(byte b) {
        this.configParameter = b;
        this.isHaveInfrared = CoffeeDataConvertUtils.getBit(this.configParameter, 0);
        this.isHaveAutoDoor = CoffeeDataConvertUtils.getBit(this.configParameter, 1);
    }
}
