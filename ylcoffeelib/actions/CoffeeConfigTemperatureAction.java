package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeConfigTemperature;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeConfigTemperatureAction extends BaseCoffeeEventAction {

    private static class staticCoffeeConfigTemperatureAction {
        /* access modifiers changed from: private */
        public static final CoffeeConfigTemperatureAction instance = new CoffeeConfigTemperatureAction();

        private staticCoffeeConfigTemperatureAction() {
        }
    }

    public static CoffeeConfigTemperatureAction getInstance() {
        return staticCoffeeConfigTemperatureAction.instance;
    }

    public void setConfigMaxAndMinWaterTemperature(byte b, byte b2) {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeConfigTemperature.sendConfigMaxAndMinWaterTemperature(CoffeeSerialPortManager.getInstance().spoDriver, b, b2));
    }
}
