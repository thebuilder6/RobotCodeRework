package frc.robot.Subsystems;

import frc.robot.Robot;
import frc.robot.Settings;
import frc.robot.Devices.Controller;

public class Control implements Subsystem
{

    public Control getInstance()
    {
        if (instance == null)
        {
            instance = new Control();
        }
        return instance;
    }

    private static Control instance = null;

    private Controller driverController;
    private Controller operatorController;

    private Control()
    {
        SubsystemManager.registerSubsystem(this);
        int numberOfControllers = Integer.parseInt(Settings.settingsList.get("NUMBEROFCONTROLLERS"));
        if (numberOfControllers >= 1)
        {
            driverController = new Controller(Settings.portList.get("XBOX_DRIVER_CONTROLLER"));
        }
        if (numberOfControllers >= 2)
        {
            operatorController = new Controller(Settings.portList.get("XBOX_OPERATOR_CONTROLLER"));
        }
    }

    @Override
    public String getName()
    {
        return "Control";
    }

    @Override
    public boolean go()
    {
        return true;
    }

    @Override
    public void log()
    {

    }

    @Override
    public void update()
    {

    }

}
