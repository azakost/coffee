package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeAllFault;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeAllFaultAction extends BaseCoffeeEventAction {
    private static CoffeeAllFaultAction instance;

    public static CoffeeAllFaultAction getInstance() {
        if (instance == null) {
            instance = new CoffeeAllFaultAction();
        }
        return instance;
    }

    public void readAllFault() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeAllFault.sendReadAllFault(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
