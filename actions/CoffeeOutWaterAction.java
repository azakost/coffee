package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeOutWater;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeOutWaterAction extends BaseCoffeeEventAction {

    private static class staticCoffeeOutWaterAction {
        /* access modifiers changed from: private */
        public static final CoffeeOutWaterAction instance = new CoffeeOutWaterAction();

        private staticCoffeeOutWaterAction() {
        }
    }

    public static CoffeeOutWaterAction getInstance() {
        return staticCoffeeOutWaterAction.instance;
    }

    public void setOutWater(byte b, short s) {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeOutWater.sendOutWater(CoffeeSerialPortManager.getInstance().spoDriver, b, s));
    }

    public void setOutWater(byte b, short s, byte b2) {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeOutWater.sendOutWater(CoffeeSerialPortManager.getInstance().spoDriver, b, s, b2));
    }
}
