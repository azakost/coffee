package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeAutoDoor;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeAutoDoorAction extends BaseCoffeeEventAction {

    private static class staticCoffeeAutoDoorAction {
        /* access modifiers changed from: private */
        public static final CoffeeAutoDoorAction instance = new CoffeeAutoDoorAction();

        private staticCoffeeAutoDoorAction() {
        }
    }

    public static CoffeeAutoDoorAction getInstance() {
        return staticCoffeeAutoDoorAction.instance;
    }

    public void autoDoorOpen() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeAutoDoor.sendAutoDoorOpen(CoffeeSerialPortManager.getInstance().spoDriver));
    }

    public void autoDoorClose() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeAutoDoor.sendAutoDoorClose(CoffeeSerialPortManager.getInstance().spoDriver));
    }

    public void electDoorOpen(int i) {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeAutoDoor.sendElectDoorOpen(CoffeeSerialPortManager.getInstance().spoDriver, i));
    }
}
