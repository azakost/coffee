package com.levending.ylcoffeelib.workstatus;

public class CoffeeMachineRunStatus {
    public IMachineRunStatusListener iMachineRunStatusListener = null;

    public interface IMachineRunStatusListener {
        void onReceiveRunStatus(int i);
    }

    private static class staticCoffeeMachineRunStatus {
        /* access modifiers changed from: private */
        public static final CoffeeMachineRunStatus instance = new CoffeeMachineRunStatus();

        private staticCoffeeMachineRunStatus() {
        }
    }

    public static CoffeeMachineRunStatus getInstance() {
        return staticCoffeeMachineRunStatus.instance;
    }

    public void setListener(IMachineRunStatusListener iMachineRunStatusListener2) {
        this.iMachineRunStatusListener = iMachineRunStatusListener2;
    }

    public void removeListener() {
        this.iMachineRunStatusListener = null;
    }

    /* access modifiers changed from: protected */
    public void setMachineRunStatus(int i) {
        if (this.iMachineRunStatusListener != null) {
            this.iMachineRunStatusListener.onReceiveRunStatus(i);
        }
    }
}
