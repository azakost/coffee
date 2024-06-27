package com.levending.ylcoffeelib.workstatus;

public class CoffeeMachineErrorStatus {
    public IMachineErrorStatusListener iMachineErrorStatusListener = null;

    public interface IMachineErrorStatusListener {
        void onReceiveErrorStatus(String str);
    }

    private static class staticCoffeeMachineErrorStatus {
        /* access modifiers changed from: private */
        public static final CoffeeMachineErrorStatus instance = new CoffeeMachineErrorStatus();

        private staticCoffeeMachineErrorStatus() {
        }
    }

    public static CoffeeMachineErrorStatus getInstance() {
        return staticCoffeeMachineErrorStatus.instance;
    }

    public void setListener(IMachineErrorStatusListener iMachineErrorStatusListener2) {
        this.iMachineErrorStatusListener = iMachineErrorStatusListener2;
    }

    public void removeListener() {
        this.iMachineErrorStatusListener = null;
    }

    /* access modifiers changed from: protected */
    public void setMachineErrorStatus(String str) {
        if (this.iMachineErrorStatusListener != null) {
            this.iMachineErrorStatusListener.onReceiveErrorStatus(str);
        }
    }
}
