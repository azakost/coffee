package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeOutColdWater;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeOutColdWaterAction extends BaseCoffeeEventAction {

    private static class staticCoffeeOutColdWaterAction {
        /* access modifiers changed from: private */
        public static final CoffeeOutColdWaterAction instance = new CoffeeOutColdWaterAction();

        private staticCoffeeOutColdWaterAction() {
        }
    }

    public static CoffeeOutColdWaterAction getInstance() {
        return staticCoffeeOutColdWaterAction.instance;
    }

    public void outColdWater(int i) {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeOutColdWater.sendOutColdWater(CoffeeSerialPortManager.getInstance().spoDriver, i));
    }
}
