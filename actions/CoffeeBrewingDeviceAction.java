package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeBrewingDevice;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeBrewingDeviceAction extends BaseCoffeeEventAction {

    private static class staticCoffeeBrewingDeviceAction {
        /* access modifiers changed from: private */
        public static final CoffeeBrewingDeviceAction instance = new CoffeeBrewingDeviceAction();

        private staticCoffeeBrewingDeviceAction() {
        }
    }

    public static CoffeeBrewingDeviceAction getInstance() {
        return staticCoffeeBrewingDeviceAction.instance;
    }

    public void setBrewingDeviceUP() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeBrewingDevice.sendBrewingDeviceUP(CoffeeSerialPortManager.getInstance().spoDriver));
    }

    public void setBrewingDeviceDown() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeBrewingDevice.sendBrewingDeviceDown(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
