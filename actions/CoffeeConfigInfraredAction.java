package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeConfigInfrared;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeConfigInfraredAction extends BaseCoffeeEventAction {

    private static class staticCoffeeConfigInfraredAction {
        /* access modifiers changed from: private */
        public static final CoffeeConfigInfraredAction instance = new CoffeeConfigInfraredAction();

        private staticCoffeeConfigInfraredAction() {
        }
    }

    public static CoffeeConfigInfraredAction getInstance() {
        return staticCoffeeConfigInfraredAction.instance;
    }

    public void configHaveInfrared() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeConfigInfrared.sendConfigHaveInfrared(CoffeeSerialPortManager.getInstance().spoDriver));
    }

    public void configNoInfrared() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeConfigInfrared.sendConfigNoInfrared(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
