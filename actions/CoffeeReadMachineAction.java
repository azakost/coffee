package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeReadStatus;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeReadMachineAction extends BaseCoffeeEventAction {

    private static class staticCoffeeReadMachineStatus {
        /* access modifiers changed from: private */
        public static final CoffeeReadMachineAction instance = new CoffeeReadMachineAction();

        private staticCoffeeReadMachineStatus() {
        }
    }

    public static CoffeeReadMachineAction getInstance() {
        return staticCoffeeReadMachineStatus.instance;
    }

    public void queryMachineStatusInfo() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        postOnActionStart();
        postOnCmdSendState(P_CoffeeReadStatus.sendReadCoffeeStatus(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
