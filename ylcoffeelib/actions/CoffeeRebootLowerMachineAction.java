package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeRebootLowerMachine;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeRebootLowerMachineAction extends BaseCoffeeEventAction {

    private static class staticCoffeeRebootLowerMachineAction {
        /* access modifiers changed from: private */
        public static final CoffeeRebootLowerMachineAction instance = new CoffeeRebootLowerMachineAction();

        private staticCoffeeRebootLowerMachineAction() {
        }
    }

    public static CoffeeRebootLowerMachineAction getInstance() {
        return staticCoffeeRebootLowerMachineAction.instance;
    }

    public void rebootLowerMachine() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeRebootLowerMachine.sendRebootLowerMachine(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
