package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.P_CoffeeClearMachine;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;
import com.yl.ylice2lib.protocol.IceTwoProtocol;

public class CoffeeClearMachineAction extends BaseCoffeeEventAction {
    public static byte clear_channel = 10;
    public byte channel_1_WaterYield = IceTwoProtocol.cmdCode_10;
    public byte channel_2_WaterYield = IceTwoProtocol.cmdCode_10;
    public byte channel_3_WaterYield = IceTwoProtocol.cmdCode_10;
    public byte channel_4_WaterYield = IceTwoProtocol.cmdCode_10;
    public byte channel_5_WaterYield = IceTwoProtocol.cmdCode_10;

    private static class staticCoffeeClearAction {
        /* access modifiers changed from: private */
        public static final CoffeeClearMachineAction instance = new CoffeeClearMachineAction();

        private staticCoffeeClearAction() {
        }
    }

    public static CoffeeClearMachineAction getInstance() {
        return staticCoffeeClearAction.instance;
    }

    public void clearMachineV12() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        P_CoffeeClearMachine.clear_channel = clear_channel;
        super.postOnCmdSendState(P_CoffeeClearMachine.sendClearV12(CoffeeSerialPortManager.getInstance().spoDriver));
    }

    public void clearMachineV13() {
        if (super.getIsTaskRunning()) {
            super.postOnActionRunningState(true);
            return;
        }
        super.postOnActionStart();
        P_CoffeeClearMachine.channel_1_WaterYield = this.channel_1_WaterYield;
        P_CoffeeClearMachine.channel_2_WaterYield = this.channel_2_WaterYield;
        P_CoffeeClearMachine.channel_3_WaterYield = this.channel_3_WaterYield;
        P_CoffeeClearMachine.channel_4_WaterYield = this.channel_4_WaterYield;
        P_CoffeeClearMachine.channel_5_WaterYield = this.channel_5_WaterYield;
        super.postOnCmdSendState(P_CoffeeClearMachine.sendClearV13(CoffeeSerialPortManager.getInstance().spoDriver));
    }
}
