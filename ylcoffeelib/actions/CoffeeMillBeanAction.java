package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeMillBean;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeMillBeanAction extends BaseCoffeeEventAction {

    private static class staticCoffeeMillBeanAction {
        /* access modifiers changed from: private */
        public static final CoffeeMillBeanAction instance = new CoffeeMillBeanAction();

        private staticCoffeeMillBeanAction() {
        }
    }

    public static CoffeeMillBeanAction getInstance() {
        return staticCoffeeMillBeanAction.instance;
    }

    public void millBean() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        super.postOnCmdSendState(P_CoffeeMillBean.sendMillBean(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
