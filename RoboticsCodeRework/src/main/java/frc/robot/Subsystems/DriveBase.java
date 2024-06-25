package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Settings;

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
            switch (Settings.getSetting("DRIVEBASE.MOTORCONTROLLER.TYPE", String.class)) {
                case "SPARKMAX":
                    //TODO Add SparkMax motor controllers
                    break;
                case "VICTORSPX":
                    //TODO Add VictorSPX motor controllers
                    break;
            }
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
        SmartDashboard.putBoolean(getName(), isActive);
        SmartDashboard.putNumber(getName() + "/FrontLeft", 0);
        SmartDashboard.putNumber(getName() + "/RearLeft", 0);
        SmartDashboard.putNumber(getName() + "/FrontRight", 0);
        SmartDashboard.putNumber(getName() + "/RearRight", 0);
    }

    @Override
    public void update() {

    }
}
