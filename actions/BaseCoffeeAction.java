package com.levending.ylcoffeelib.actions;

import android.os.Handler;

public abstract class BaseCoffeeAction {
    private boolean isOverTime = false;
    private Handler mHandlerTimeCheck = new Handler();
    public int overTimeValue = 1000;
    private TimeRunnableCheck timeRunnableCheck = null;

    /* access modifiers changed from: protected */
    public abstract void overTimeMethod();

    private class TimeRunnableCheck implements Runnable {
        private TimeRunnableCheck() {
        }

        public void run() {
            BaseCoffeeAction.this.setOvertimeState(true);
            BaseCoffeeAction.this.stopOvertimeCheck();
            BaseCoffeeAction.this.overTimeMethod();
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void setOvertimeState(boolean z) {
        this.isOverTime = z;
    }

    /* access modifiers changed from: protected */
    public synchronized boolean getOverTimeState() {
        return this.isOverTime;
    }

    /* access modifiers changed from: protected */
    public void startOvertimeCheck() {
        this.timeRunnableCheck = new TimeRunnableCheck();
        this.mHandlerTimeCheck.postDelayed(this.timeRunnableCheck, (long) this.overTimeValue);
    }

    /* access modifiers changed from: protected */
    public void stopOvertimeCheck() {
        if (this.timeRunnableCheck != null) {
            this.mHandlerTimeCheck.removeCallbacks(this.timeRunnableCheck);
            this.timeRunnableCheck = null;
        }
    }
}
