package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.CoffeePackage;

public class BaseCoffeeEventAction extends BaseCoffeeAction {
    public IActionListener actionListener = null;
    protected boolean isTaskRunning = false;

    public void setListener(IActionListener iActionListener) {
        this.actionListener = iActionListener;
    }

    public void removeListener() {
        this.actionListener = null;
    }

    public synchronized void setIsTaskRunning(boolean z) {
        this.isTaskRunning = z;
    }

    public synchronized boolean getIsTaskRunning() {
        return this.isTaskRunning;
    }

    public void postOnActionStart() {
        this.isTaskRunning = true;
        super.setOvertimeState(false);
        super.startOvertimeCheck();
        if (this.actionListener != null) {
            this.actionListener.onActionStart();
        }
    }

    public void postOnActionRunningState(boolean z) {
        if (this.actionListener != null) {
            this.actionListener.onActionRunningState(z);
        }
    }

    public void postOnCmdSendState(boolean z) {
        if (this.actionListener != null) {
            this.actionListener.onCmdSendState(z);
        }
    }

    public void postOnReceiveBackResult(CoffeePackage coffeePackage) {
        super.stopOvertimeCheck();
        if (this.actionListener != null) {
            this.actionListener.onReceiveBackResult(coffeePackage);
        }
        postOnActionEnd();
    }

    public void setReceiveBackResult(CoffeePackage coffeePackage) {
        if (!super.getOverTimeState()) {
            postOnReceiveBackResult(coffeePackage);
        }
    }

    private void postOnAnswerOvertime(int i) {
        if (this.actionListener != null) {
            this.actionListener.onAnswerOvertime(i);
        }
    }

    private void postOnActionEnd() {
        this.isTaskRunning = false;
        if (this.actionListener != null) {
            this.actionListener.onActionEnd();
        }
    }

    /* access modifiers changed from: protected */
    public void overTimeMethod() {
        postOnAnswerOvertime(this.overTimeValue);
        postOnActionEnd();
    }
}
