package frc.robot.Subsystems;

import com.kauailabs.navx.frc.AHRS;
//https://dev.studica.com/releases/2024/NavX.json

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Settings;

public class IMU implements Subsystem {
    AHRS ahrs;

    private static IMU instance = null;

    public static IMU getInstance() {
        if (instance == null) {
            instance = new IMU();
        }
        return instance;
    }

    public IMU() {
        SubsystemManager.registerSubsystem(this);
    }

    private boolean isActive;

    @Override
    public String getName() {
        return "IMU";
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void initialize() {
        if (Settings.getSetting("IMU") != null && Settings.getSetting("IMU").equals("AHRS")) {
            try {
                ahrs = new AHRS(SPI.Port.kMXP);
                isActive = true;
            } catch (Exception e) {
                ahrs = null;
                isActive = false;
                System.out.println(
                        "Could not get ahrs; did you drop metal shavings in the RoboRio again?");
            }
        } else {
            isActive = false;
            ahrs = null;
        }

    }

    @Override
    public void log() {
        SmartDashboard.putBoolean(getName() + "/isActive", isActive);
        if (ahrs != null) {
            SmartDashboard.putNumber(getName() + "/yaw", ahrs.getAngle());
            SmartDashboard.putNumber(getName() + "/pitch", ahrs.getPitch());
            SmartDashboard.putNumber(getName() + "/roll", ahrs.getRoll());
        }
    }

    @Override
    public void update() {
    }

    public double getYaw() {
        if (ahrs != null) {
            return ahrs.getAngle();
        } else {
            return 0;
        }
    }

    public void reset() {
        if (ahrs != null) {
            ahrs.reset();
        }
    }

}
