package com.levending.ylcoffeelib.actions;

import com.levending.ylcoffeelib.protocol.CoffeePackage;

public interface IActionListener {
    void onActionEnd();

    void onActionRunningState(boolean z);

    void onActionStart();

    void onAnswerOvertime(int i);

    void onCmdSendState(boolean z);

    void onReceiveBackResult(CoffeePackage coffeePackage);
}
