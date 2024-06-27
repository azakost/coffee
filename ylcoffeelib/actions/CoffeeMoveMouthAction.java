package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeMoveMouth;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeMoveMouthAction extends BaseCoffeeEventAction {

    private static class staticCoffeeMoveMouthAction {
        /* access modifiers changed from: private */
        public static final CoffeeMoveMouthAction instance = new CoffeeMoveMouthAction();

        private staticCoffeeMoveMouthAction() {
        }
    }

    public static CoffeeMoveMouthAction getInstance() {
        return staticCoffeeMoveMouthAction.instance;
    }

    public void setMoveMouthBuiltOut() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeMoveMouth.sendMoveMouthBuiltOut(CoffeeSerialPortManager.getInstance().spoDriver));
    }

    public void setMoveMouthBuiltIn() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeMoveMouth.sendMoveMouthBuiltIn(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
