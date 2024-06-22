package frc.robot.Subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
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
    }

    DriveBase driveBase;

    private boolean isActive;

    public void initialize()
    {
        if (Settings.getSetting("CONTROL").equals("DISABLE"))
        {
            isActive = false;
            return;
        }
        if (Settings.getSetting("PORTMAP.XBOX_DRIVER_CONTROLLER") == null)
        {
            driverController = new Controller(Settings.getSetting("PORTMAP.XBOX_DRIVER_CONTROLLER"));
        }
        if (Settings.getSetting("PORTMAP.XBOX_OPERATOR_CONTROLLER") == null)
        {
            operatorController = new Controller(Settings.getSetting("PORTMAP.XBOX_OPERATOR_CONTROLLER"));
        }
        driveBase = DriveBase.getInstance();

        isActive = true;
    }

    @Override
    public String getName()
    {
        return "Control";
    }

    @Override
    public boolean isActive()
    {
        return isActive;
    }

    @Override
    public void log()
    {

    }

    private void driveBaseControl()
    {
        if (driveBase.isActive() && driverController != null)
        {

        }
    }

    public void initSendable(SendableBuilder builder)
    {
        builder.setSmartDashboardType("Control");
        builder.addBooleanProperty("isActive", this::isActive, isActive -> this.isActive = isActive);
        builder.addStringProperty("XBOX_DRIVER_CONTROLLER", () -> Settings.getSetting("PORTMAP.XBOX_DRIVER_CONTROLLER"), null);
    }

    @Override
    public void update()
    {
        driveBaseControl();

    }

}
