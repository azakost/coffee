package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeClearRunResult;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeClearRunResultAction extends BaseCoffeeEventAction {

    private static class staticCoffeeClearRunResultAction {
        /* access modifiers changed from: private */
        public static final CoffeeClearRunResultAction instance = new CoffeeClearRunResultAction();

        private staticCoffeeClearRunResultAction() {
        }
    }

    public static CoffeeClearRunResultAction getInstance() {
        return staticCoffeeClearRunResultAction.instance;
    }

    public void clearRunResult() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeClearRunResult.sendClearRunResult(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
