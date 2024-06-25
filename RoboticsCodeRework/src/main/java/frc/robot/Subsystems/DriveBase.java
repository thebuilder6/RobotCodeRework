package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Settings;
import frc.robot.Devices.MotorControllersDriveBase;

public class DriveBase implements Subsystem {
    private static DriveBase instance = null;

    public static DriveBase getInstance() {
        if (instance == null) {
            instance = new DriveBase();
        }
        return instance;
    }

    private DriveBase() {
        SubsystemManager.registerSubsystem(this);

    }

    private boolean isActive;
    private boolean motorControllersEnabled;
    private MotorControllersDriveBase motorControllerPairLeft;
    private MotorControllersDriveBase motorControllerPairRight;

    @Override
    public void initialize() {
        if (Settings.getSetting("DRIVEBASE") != null && Settings.getSetting("DRIVEBASE").equals("DISABLE")) {
            isActive = false;
            return;
        }

        if ((Settings.getSetting("PORTMAP.FrontLeft") == null) ||
                (Settings.getSetting("PORTMAP.RearLeft") == null) ||
                (Settings.getSetting("PORTMAP.FrontRight") == null) ||
                (Settings.getSetting("PORTMAP.RearRight") == null) ||
                (Settings.getSetting("PORTMAP.FrontLeft", Integer.class) < 0) ||
                (Settings.getSetting("PORTMAP.RearLeft", Integer.class) < 0) ||
                (Settings.getSetting("PORTMAP.FrontRight", Integer.class) < 0) ||
                (Settings.getSetting("PORTMAP.RearRight", Integer.class) < 0)) {
            motorControllersEnabled = false;
            isActive = false;
            return;
        } else {
            motorControllersEnabled = true;
            motorControllerPairLeft = new MotorControllersDriveBase(
                    Settings.getSetting("PORTMAP.FrontLeft", Integer.class),
                    Settings.getSetting("PORTMAP.RearLeft", Integer.class), true);
            motorControllerPairRight = new MotorControllersDriveBase(
                    Settings.getSetting("PORTMAP.FrontRight", Integer.class),
                    Settings.getSetting("PORTMAP.RearRight", Integer.class), false);
        }

        isActive = true;
    }

    @Override
    public String getName() {
        return "DriveBase";
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void log() {
        SmartDashboard.putBoolean(getName() + "/isActive", isActive);
        SmartDashboard.putBoolean(getName() + "/LeftIsActive", motorControllerPairLeft != null);
        SmartDashboard.putBoolean(getName() + "/RightIsActive", motorControllerPairRight != null);
    }

    @Override
    public void update() {

    }
}
