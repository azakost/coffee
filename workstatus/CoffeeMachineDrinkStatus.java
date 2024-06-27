package com.levending.ylcoffeelib.workstatus;

public class CoffeeMachineDrinkStatus {
    public IDrinkMakeResultListener iDrinkMakeResultListener = null;

    public interface IDrinkMakeResultListener {
        void onCheckFinish(int i);

        void onCheckIng(int i);

        void onClearFinish(int i);

        void onClearIng(int i);

        void onDownCupIng(int i);

        void onDownCupSuccess(int i);

        void onFree(int i);

        void onMakeFail(int i);

        void onMakeIng(int i);

        void onMakeSuccess(int i);
    }

    private static class staticCoffeeMachineDrinkStatus {
        /* access modifiers changed from: private */
        public static final CoffeeMachineDrinkStatus instance = new CoffeeMachineDrinkStatus();

        private staticCoffeeMachineDrinkStatus() {
        }
    }

    public static CoffeeMachineDrinkStatus getInstance() {
        return staticCoffeeMachineDrinkStatus.instance;
    }

    public void setListener(IDrinkMakeResultListener iDrinkMakeResultListener2) {
        this.iDrinkMakeResultListener = iDrinkMakeResultListener2;
    }

    public void removeListener() {
        this.iDrinkMakeResultListener = null;
    }

    /* access modifiers changed from: protected */
    public void setDrinkMakeResult(byte b) {
        switch (b) {
            case 0:
                if (this.iDrinkMakeResultListener != null) {
                    this.iDrinkMakeResultListener.onFree(b);
                    return;
                }
                return;
            case 1:
                if (this.iDrinkMakeResultListener != null) {
                    this.iDrinkMakeResultListener.onMakeIng(b);
                    return;
                }
                return;
            case 2:
                if (this.iDrinkMakeResultListener != null) {
                    this.iDrinkMakeResultListener.onMakeSuccess(b);
                    return;
                }
                return;
            case 3:
                if (this.iDrinkMakeResultListener != null) {
                    this.iDrinkMakeResultListener.onMakeFail(b);
                    return;
                }
                return;
            default:
                switch (b) {
                    case 11:
                        if (this.iDrinkMakeResultListener != null) {
                            this.iDrinkMakeResultListener.onDownCupIng(b);
                            return;
                        }
                        return;
                    case 12:
                        if (this.iDrinkMakeResultListener != null) {
                            this.iDrinkMakeResultListener.onDownCupSuccess(b);
                            return;
                        }
                        return;
                    default:
                        switch (b) {
                            case 21:
                                if (this.iDrinkMakeResultListener != null) {
                                    this.iDrinkMakeResultListener.onClearIng(b);
                                    return;
                                }
                                return;
                            case 22:
                                if (this.iDrinkMakeResultListener != null) {
                                    this.iDrinkMakeResultListener.onClearFinish(b);
                                    return;
                                }
                                return;
                            case 23:
                                if (this.iDrinkMakeResultListener != null) {
                                    this.iDrinkMakeResultListener.onCheckIng(b);
                                    return;
                                }
                                return;
                            case 24:
                                if (this.iDrinkMakeResultListener != null) {
                                    this.iDrinkMakeResultListener.onCheckFinish(b);
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                }
        }
    }
}
