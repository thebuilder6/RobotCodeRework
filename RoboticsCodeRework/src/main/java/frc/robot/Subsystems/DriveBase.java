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
        if (Settings.getSetting("subsystem_drivebase_enabled", Boolean.class)) {
            SubsystemManager.registerSubsystem(this);
            SubsystemManager.registerSubsystem(instance);
        } else {
            isActive = false;
        }
    }

    private boolean isActive;
    private boolean motorControllersEnabled;
    private MotorControllersDriveBase motorControllerPairLeft;
    private MotorControllersDriveBase motorControllerPairRight;

    @Override
    public void initialize() {
        if ((Settings.getSetting("portmap_drive_motor_front_left", -1) < 0) ||
                (Settings.getSetting("portmap_drive_motor_rear_left", -1) < 0) ||
                (Settings.getSetting("portmap_drive_motor_front_right", -1) < 0) ||
                (Settings.getSetting("portmap_drive_motor_rear_right", -1) < 0)) {
            motorControllersEnabled = false;
            isActive = false;
            return;
        } else {
            motorControllersEnabled = true;
            motorControllerPairLeft = new MotorControllersDriveBase(
                    Settings.getSetting("portmap_drive_motor_front_left", Integer.class),
                    Settings.getSetting("portmap_drive_motor_rear_left", Integer.class), true);
            motorControllerPairRight = new MotorControllersDriveBase(
                    Settings.getSetting("portmap_drive_motor_front_right", Integer.class),
                    Settings.getSetting("portmap_drive_motor_rear_right", Integer.class), false);
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
