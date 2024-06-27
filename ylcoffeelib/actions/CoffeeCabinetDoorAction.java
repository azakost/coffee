package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeCabinetDoor;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeCabinetDoorAction extends BaseCoffeeEventAction {
    private static CoffeeCabinetDoorAction instance;

    public static CoffeeCabinetDoorAction getInstance() {
        if (instance == null) {
            instance = new CoffeeCabinetDoorAction();
        }
        return instance;
    }

    public void openEleLock() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeCabinetDoor.sendOpenEleLock(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
