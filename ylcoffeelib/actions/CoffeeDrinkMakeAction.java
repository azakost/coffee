package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeDrinkMak;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;
import java.util.Random;

public class CoffeeDrinkMakeAction extends BaseCoffeeEventAction {
    short currentFlowCode = ((short) new Random().nextInt(1000));

    private static class staticCoffeeDrinkMakeAction {
        /* access modifiers changed from: private */
        public static final CoffeeDrinkMakeAction instance = new CoffeeDrinkMakeAction();

        private staticCoffeeDrinkMakeAction() {
        }
    }

    public static CoffeeDrinkMakeAction getInstance() {
        return staticCoffeeDrinkMakeAction.instance;
    }

    public void clearListDrink() {
        P_CoffeeDrinkMak.clearListDrink();
    }

    public void addDrink(byte b, byte b2, short s, byte b3, byte b4) {
        P_CoffeeDrinkMak.addDrink(b, b2, s, b3, b4);
    }

    public void makeDrink(float f) {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        P_CoffeeDrinkMak.setFlowCode(getFlowCode());
        P_CoffeeDrinkMak.setSugarTime((byte) ((int) (f * 10.0f)));
        super.postOnCmdSendState(P_CoffeeDrinkMak.sendMakeDrink(CoffeeSerialPortManager.getInstance().spoDriver, false));
    }

    public void makeDrink308E1(byte b) {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        P_CoffeeDrinkMak.setFlowCode(getFlowCode());
        P_CoffeeDrinkMak.setSugarTime(b);
        super.postOnCmdSendState(P_CoffeeDrinkMak.sendMakeDrink(CoffeeSerialPortManager.getInstance().spoDriver, true));
    }

    private short getFlowCode() {
        if (this.currentFlowCode >= 32760) {
            this.currentFlowCode = 0;
        }
        this.currentFlowCode = (short) (this.currentFlowCode + 1);
        return this.currentFlowCode;
    }
}
