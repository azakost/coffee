package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeAutoCheck;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeAutoCheckAction extends BaseCoffeeEventAction {

    private static class staticCoffeeAutoCheckAction {
        /* access modifiers changed from: private */
        public static final CoffeeAutoCheckAction instance = new CoffeeAutoCheckAction();

        private staticCoffeeAutoCheckAction() {
        }
    }

    public static CoffeeAutoCheckAction getInstance() {
        return staticCoffeeAutoCheckAction.instance;
    }

    public void startAutoCheck() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeAutoCheck.sendAutoCheck(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
