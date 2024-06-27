package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeOutPowder;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeOutPowderAction extends BaseCoffeeEventAction {

    private static class staticCoffeeOutPowderAction {
        /* access modifiers changed from: private */
        public static final CoffeeOutPowderAction instance = new CoffeeOutPowderAction();

        private staticCoffeeOutPowderAction() {
        }
    }

    public static CoffeeOutPowderAction getInstance() {
        return staticCoffeeOutPowderAction.instance;
    }

    public void outPowder() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeOutPowder.sendOutPowder(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
