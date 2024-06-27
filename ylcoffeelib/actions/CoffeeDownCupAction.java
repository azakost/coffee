package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeDownCup;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeDownCupAction extends BaseCoffeeEventAction {

    private static class staticCoffeeDownCupAction {
        /* access modifiers changed from: private */
        public static final CoffeeDownCupAction instance = new CoffeeDownCupAction();

        private staticCoffeeDownCupAction() {
        }
    }

    public static CoffeeDownCupAction getInstance() {
        return staticCoffeeDownCupAction.instance;
    }

    public void downCup() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeDownCup.sendDownCup(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
