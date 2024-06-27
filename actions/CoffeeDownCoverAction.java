package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeDownCover;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeDownCoverAction extends BaseCoffeeEventAction {

    private static class staticCoffeeDownCoverAction {
        /* access modifiers changed from: private */
        public static final CoffeeDownCoverAction instance = new CoffeeDownCoverAction();

        private staticCoffeeDownCoverAction() {
        }
    }

    public static CoffeeDownCoverAction getInstance() {
        return staticCoffeeDownCoverAction.instance;
    }

    public void downCover() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeDownCover.sendDownCover(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
