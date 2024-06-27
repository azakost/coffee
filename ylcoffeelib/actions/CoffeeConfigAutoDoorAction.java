package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeConfigAutoDoor;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeConfigAutoDoorAction extends BaseCoffeeEventAction {

    private static class staticCoffeeConfigAutoDoorAction {
        /* access modifiers changed from: private */
        public static final CoffeeConfigAutoDoorAction instance = new CoffeeConfigAutoDoorAction();

        private staticCoffeeConfigAutoDoorAction() {
        }
    }

    public static CoffeeConfigAutoDoorAction getInstance() {
        return staticCoffeeConfigAutoDoorAction.instance;
    }

    public void configHaveAutoDoor() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeConfigAutoDoor.sendConfigHaveAutoDoor(CoffeeSerialPortManager.getInstance().spoDriver));
    }

    public void configNoAutoDoor() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeConfigAutoDoor.sendConfigNoAutoDoor(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
