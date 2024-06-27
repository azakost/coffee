package com.levending.ylcoffeelib.workstatus;

import com.igexin.push.core.b;
import com.levending.ylcoffeelib.actions.CoffeeReadMachineAction;
import com.levending.ylcoffeelib.actions.IActionListener;
import com.levending.ylcoffeelib.bean.CoffeeDeviceStatus;
import com.levending.ylcoffeelib.common.CoffeeDataConvertUtils;
import com.levending.ylcoffeelib.common.CoffeeLibLog;
import com.levending.ylcoffeelib.protocol.CoffeePackage;
import com.levending.ylspcore.common.SPCoreConvertUtils;

public class CoffeeMachineCheckStatus {
    /* access modifiers changed from: private */
    public int delay = 0;
    /* access modifiers changed from: private */
    public boolean isStopThread = false;
    MyThread myThread = null;
    /* access modifiers changed from: private */
    public int period = 5000;

    private static class staticCoffeeMachineCheck {
        /* access modifiers changed from: private */
        public static final CoffeeMachineCheckStatus instance = new CoffeeMachineCheckStatus();

        private staticCoffeeMachineCheck() {
        }
    }

    public static CoffeeMachineCheckStatus getInstance() {
        return staticCoffeeMachineCheck.instance;
    }

    public void startCheckStatus(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("startCheckStatus---开始实时检测 ");
        sb.append(i);
        sb.append("|");
        sb.append(i2);
        sb.append("|myThread:");
        sb.append(this.myThread == null ? b.k : "no null");
        CoffeeLibLog.println(sb.toString());
        this.delay = i;
        this.period = i2;
        if (this.myThread == null) {
            this.myThread = new MyThread();
            this.myThread.start();
        }
    }

    public void stopCheckStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("stopCheckStatus---停止实时检测 myThread:");
        sb.append(this.myThread == null ? b.k : "no null");
        CoffeeLibLog.println(sb.toString());
        if (this.myThread != null) {
            this.isStopThread = true;
            this.myThread.interrupt();
            this.myThread = null;
        }
    }

    private class MyThread extends Thread {
        private MyThread() {
        }

        public void run() {
            try {
                sleep((long) CoffeeMachineCheckStatus.this.delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
                CoffeeLibLog.println("MyThread run()---delay:" + e.toString());
            }
            while (true) {
                if (!isInterrupted() || CoffeeMachineCheckStatus.this.isStopThread) {
                    CoffeeLibLog.println("MyThread run()---发送一次检测");
                    CoffeeReadMachineAction.getInstance().queryMachineStatusInfo();
                    try {
                        sleep((long) CoffeeMachineCheckStatus.this.period);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        CoffeeLibLog.println("MyThread run()---period:" + e2.toString());
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public void monitorCoffeeReadMachineAction() {
        CoffeeReadMachineAction.getInstance().setListener(new IActionListener() {
            public void onActionEnd() {
            }

            public void onActionRunningState(boolean z) {
            }

            public void onActionStart() {
            }

            public void onCmdSendState(boolean z) {
            }

            public void onReceiveBackResult(CoffeePackage coffeePackage) {
                CoffeeDeviceStatus access$500 = CoffeeMachineCheckStatus.this.parseBackResult(coffeePackage);
                CoffeeMachineAllStatus.getInstance().setMachineAllStatus(access$500);
                CoffeeMachineDrinkStatus.getInstance().setDrinkMakeResult(access$500.drinkDoResult);
                CoffeeMachineErrorStatus instance = CoffeeMachineErrorStatus.getInstance();
                instance.setMachineErrorStatus(access$500.errorType + "-" + access$500.errorNum);
                CoffeeMachineRunStatus.getInstance().setMachineRunStatus(access$500.currentRunAction);
            }

            public void onAnswerOvertime(int i) {
                CoffeeMachineAllStatus.getInstance().setMachineAllStatusOvertime(i);
            }
        });
    }

    /* access modifiers changed from: private */
    public CoffeeDeviceStatus parseBackResult(CoffeePackage coffeePackage) {
        CoffeeDeviceStatus coffeeDeviceStatus = new CoffeeDeviceStatus();
        coffeeDeviceStatus.deviceType = coffeePackage.getDeviceType();
        coffeeDeviceStatus.deviceCode = coffeePackage.getDeviceAddress();
        byte[] packageDataContent = coffeePackage.getPackageDataContent();
        coffeeDeviceStatus.setErrorCode(packageDataContent[0]);
        coffeeDeviceStatus.currentRunAction = packageDataContent[1];
        coffeeDeviceStatus.drinkDoResult = packageDataContent[2];
        coffeeDeviceStatus.setConfigParameter(packageDataContent[3]);
        char[] charArray = SPCoreConvertUtils.byteToBit(packageDataContent[4]).toCharArray();
        coffeeDeviceStatus.isHaveCup = charArray[charArray.length - 1] == '1';
        coffeeDeviceStatus.isCupDoorOpen = charArray[charArray.length - 2] == '1';
        coffeeDeviceStatus.isCabinetOpen = charArray[charArray.length - 3] == '1';
        coffeeDeviceStatus.currentWaterTemperature = CoffeeDataConvertUtils.byteToInt(packageDataContent[5]);
        coffeeDeviceStatus.maxWaterTemperature = packageDataContent[6];
        coffeeDeviceStatus.minWaterTemperature = packageDataContent[7];
        coffeeDeviceStatus.remainingWater = CoffeeDataConvertUtils.byteToShort(new byte[]{packageDataContent[8], packageDataContent[9]});
        return coffeeDeviceStatus;
    }
}
