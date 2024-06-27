package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeReadOptional;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeReadOptionalAction extends BaseCoffeeEventAction {
    private static CoffeeReadOptionalAction instance;
    private int doorType;
    private boolean hasBeanMarquee;
    private boolean hasGermicidalLamp;
    private int mouthType;

    public static CoffeeReadOptionalAction getInstance() {
        if (instance == null) {
            instance = new CoffeeReadOptionalAction();
        }
        return instance;
    }

    public void readOptional() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeReadOptional.sendReadOptional(CoffeeSerialPortManager.getInstance().spoDriver));
    }

    public int getDoorType() {
        return this.doorType;
    }

    public void setDoorType(int i) {
        this.doorType = i;
    }

    public int getMouthType() {
        return this.mouthType;
    }

    public void setMouthType(int i) {
        this.mouthType = i;
    }

    public boolean isHasGermicidalLamp() {
        return this.hasGermicidalLamp;
    }

    public void setHasGermicidalLamp(boolean z) {
        this.hasGermicidalLamp = z;
    }

    public boolean isHasBeanMarquee() {
        return this.hasBeanMarquee;
    }

    public void setHasBeanMarquee(boolean z) {
        this.hasBeanMarquee = z;
    }
}
