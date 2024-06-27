package com.levending.ylcoffeelib.workstatus;

import com.levending.ylcoffeelib.bean.CoffeeDeviceStatus;

public class CoffeeMachineAllStatus {
    public IMachineAllStatusListener iMachineAllStatusListener = null;

    public interface IMachineAllStatusListener {
        void onReceiveAllStatus(CoffeeDeviceStatus coffeeDeviceStatus);

        void onReceiveAllStatusOvertime(int i);
    }

    private static class staticCoffeeMachineAllStatus {
        /* access modifiers changed from: private */
        public static final CoffeeMachineAllStatus instance = new CoffeeMachineAllStatus();

        private staticCoffeeMachineAllStatus() {
        }
    }

    public static CoffeeMachineAllStatus getInstance() {
        return staticCoffeeMachineAllStatus.instance;
    }

    public void setListener(IMachineAllStatusListener iMachineAllStatusListener2) {
        this.iMachineAllStatusListener = iMachineAllStatusListener2;
    }

    public void removeListener() {
        this.iMachineAllStatusListener = null;
    }

    /* access modifiers changed from: protected */
    public void setMachineAllStatus(CoffeeDeviceStatus coffeeDeviceStatus) {
        if (this.iMachineAllStatusListener != null) {
            this.iMachineAllStatusListener.onReceiveAllStatus(coffeeDeviceStatus);
        }
    }

    /* access modifiers changed from: protected */
    public void setMachineAllStatusOvertime(int i) {
        if (this.iMachineAllStatusListener != null) {
            this.iMachineAllStatusListener.onReceiveAllStatusOvertime(i);
        }
    }
}
