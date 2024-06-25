package frc.robot.Subsystems;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Settings;
import frc.robot.Devices.Controller;

public class Control implements Subsystem {

    public Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

    private static Control instance = null;

    private Controller driverController;
    private Controller operatorController;

    private Control() {
        SubsystemManager.registerSubsystem(this);
    }

    DriveBase driveBase;

    private boolean isActive;
    private boolean hasDriverController;
    private boolean hasOperatorController;

    @Override
    public void initialize() {
        if (Settings.getSetting("CONTROL").equals("DISABLE")) {
            isActive = false;
            return;
        }
        hasDriverController = Settings.getSetting("PORTMAP.XBOX_DRIVER_CONTROLLER") != null
                && Settings.getSetting("PORTMAP.XBOX_DRIVER_CONTROLLER", Integer.class) >= 0;
        hasOperatorController = Settings.getSetting("PORTMAP.XBOX_OPERATOR_CONTROLLER") != null
                && Settings.getSetting("PORTMAP.XBOX_OPERATOR_CONTROLLER", Integer.class) >= 0;

        if (hasDriverController) {
            driverController = new Controller(Settings.getSetting("PORTMAP.XBOX_DRIVER_CONTROLLER"));
        }
        if (hasOperatorController) {
            operatorController = new Controller(Settings.getSetting("PORTMAP.XBOX_OPERATOR_CONTROLLER"));
        }
        driveBase = DriveBase.getInstance();

        isActive = true;
    }

    @Override
    public String getName() {
        return "Control";
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    public void teleop() {
        driveBaseControl();
    }

    @Override
    public void log() {
        SmartDashboard.putBoolean(getName() + "/isActive", isActive);
        if (hasDriverController) {
            SmartDashboard.putBoolean(getName() + "/driverController/connected", driverController.isConnected());
            SmartDashboard.putNumber(getName() + "/driverController/port", driverController.getPort());
            //driverController.log();
        }
        if (hasOperatorController) {
            SmartDashboard.putBoolean(getName() + "/operatorController/connected", operatorController.isConnected());
            SmartDashboard.putNumber(getName() + "/operatorController/port", operatorController.getPort());
            //operatorController.log();
        }
    }

    private void driveBaseControl() {
        if (driveBase.isActive() && driverController != null) {

        }
    }

    @Override
    public void update() {

    }

}
