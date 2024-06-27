package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeReadMachineVersion;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeReadMachineVersionAction extends BaseCoffeeEventAction {
    public String currentMachineVersion = "";

    private static class staticCoffeeReadMachineVersionAction {
        /* access modifiers changed from: private */
        public static final CoffeeReadMachineVersionAction instance = new CoffeeReadMachineVersionAction();

        private staticCoffeeReadMachineVersionAction() {
        }
    }

    public static CoffeeReadMachineVersionAction getInstance() {
        return staticCoffeeReadMachineVersionAction.instance;
    }

    public void readMachineVersion() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeReadMachineVersion.sendReadMachineVersion(CoffeeSerialPortManager.getInstance().spoDriver));
    }

    public void setCurrentMachineVersion(String str) {
        this.currentMachineVersion = str;
    }
}
