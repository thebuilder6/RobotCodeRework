package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Settings;

public class SmartDashBoardIO implements Subsystem {

    private static SmartDashBoardIO instance = null;

    public static SmartDashBoardIO getInstance() {
        if (instance == null) {
            instance = new SmartDashBoardIO();
        }
        return instance;
    }

    private boolean isActive;

    private SmartDashBoardIO() {
        if (Settings.getSetting("subsystem_smartdashboard_enabled", true)) {
            SubsystemManager.registerSubsystem(this);
        } else {
            isActive = false;
        }
    }

    @Override
    public void initialize() {
        SmartDashboard.putNumber("Input1", 0);
        SmartDashboard.putNumber("Input2", 0);
        SmartDashboard.putNumber("output", 0);
        SmartDashboard.putBoolean("calculate", false);
        SmartDashboard.putString("Command Line", "");
        SmartDashboard.putBoolean("Enter Command", false);
        isActive = true;
    }

    @Override
    public String getName() {
        return "SmartDashBoardIO";
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void log() {

    }

    private void calculator() {
        double a = SmartDashboard.getNumber("Input1", 0);
        double b = SmartDashboard.getNumber("Input2", 0);
        if (SmartDashboard.getBoolean("calculate", true)) {
            SmartDashboard.putNumber("output", a + b);
            SmartDashboard.putBoolean("calculate", false);
        }

    }

    @Override
    public void update() {
        calculator();

    }
}
