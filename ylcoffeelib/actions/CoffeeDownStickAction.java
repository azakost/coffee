package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeDownStick;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeDownStickAction extends BaseCoffeeEventAction {

    private static class staticCoffeeDownStickAction {
        /* access modifiers changed from: private */
        public static final CoffeeDownStickAction instance = new CoffeeDownStickAction();

        private staticCoffeeDownStickAction() {
        }
    }

    public static CoffeeDownStickAction getInstance() {
        return staticCoffeeDownStickAction.instance;
    }

    public void downStick() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeDownStick.sendDownStick(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
