package com.levending.ylcoffeelib.device;

import com.levending.ylcoffeelib.common.CoffeeLibLog;
import com.levending.ylcoffeelib.serialport.CoffeeSerialPortManager;

public class CoffeeDeviceController {
    public boolean initCoffeeMachine(String str, String str2, int i, int i2) {
        CoffeeLibLog.println("CoffeeSerialPortManager->initCoffeeMachine()");
        CoffeeSerialPortManager.getInstance().configSerialPort(str, str2, i, i2);
        boolean initSerialPort = CoffeeSerialPortManager.getInstance().initSerialPort();
        CoffeeLibLog.println("CoffeeSerialPortManager->initCoffeeMachine() backValueInitPort:" + initSerialPort);
        return initSerialPort;
    }

    public boolean startCoffeeMachine() {
        CoffeeLibLog.println("CoffeeDeviceController->startCoffeeMachine()");
        return CoffeeSerialPortManager.getInstance().openSerialPort();
    }

    public void stopCoffeeMachine() {
        CoffeeSerialPortManager.getInstance().closeSerialPort();
    }
}
