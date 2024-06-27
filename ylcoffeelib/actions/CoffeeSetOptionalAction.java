package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeSetOptional;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeSetOptionalAction extends BaseCoffeeEventAction {
    private static CoffeeSetOptionalAction instance;

    public static CoffeeSetOptionalAction getInstance() {
        if (instance == null) {
            instance = new CoffeeSetOptionalAction();
        }
        return instance;
    }

    public void setOptional(int i, int i2, boolean z, boolean z2) {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeSetOptional.sendSetOptional(CoffeeSerialPortManager.getInstance().spoDriver, i, i2, z, z2));
    }
}
