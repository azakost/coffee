package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeOpenGerLamp;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeOpenGerLampAction extends BaseCoffeeEventAction {
    private static CoffeeOpenGerLampAction instance;

    public static CoffeeOpenGerLampAction getInstance() {
        if (instance == null) {
            instance = new CoffeeOpenGerLampAction();
        }
        return instance;
    }

    public void openGerLamp(int i, float f) {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeOpenGerLamp.sendOpenGerLamp(CoffeeSerialPortManager.getInstance().spoDriver, i, f));
    }
}
