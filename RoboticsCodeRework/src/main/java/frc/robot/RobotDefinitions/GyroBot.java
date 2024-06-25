package frc.robot.RobotDefinitions;

import java.util.HashMap;
import java.util.Map;

import frc.robot.Settings;
import frc.robot.Subsystems.DriveBase;
import frc.robot.Subsystems.SmartDashBoardIO;

public class GyroBot implements RobotDefinition {

    private static Map<String, Object> settings = new HashMap<>();

    public static void loadSettings() {
        // Load settings from a file or any other source
        // and populate the settings map
        settings.put("DRIVEBASE.MOTORCONTROLLER.TYPE", "VICTORSPX");

        for (PortMap portMap : PortMap.values()) {
            settings.put("PORTMAP." + portMap.name(), Integer.toString(portMap.value));
        }

        // ...

    }

    public boolean transferSettings() {
        loadSettings();
        Settings.appendBotSettings(settings);
        return true;
    }

    public boolean initializeSubsystems() {
        transferSettings();

        SmartDashBoardIO.getInstance();
        DriveBase.getInstance();
        // Add subsystems here
        return true;
    }

    HashMap<String, Integer> portList = new HashMap<String, Integer>();

    public enum PortMap {
        XBOX_DRIVER_CONTROLLER(0),
        XBOX_OPERATOR_CONTROLLER(1),

        CLIMBERMOTORLEFT(-4),
        CLIMBERMOTORRIGHT(-5),

        CLIMBERLEFTENCODER_A(-1),
        CLIMBERLEFTENCODER_B(-1),
        CLIMBERRIGHTENCODER_A(-1),
        CLIMBERRIGHTENCODER_B(-1),

        INTAKEMOTORPIVOT(-1),
        INTAKEMOTORROLLER(-1),
        INTAKEPIVOTENCODER(-1),
        INTAKELIMITSWITCH(-1),

        FRONTRIGHT(3),
        REARRIGHT(2),
        FRONTLEFT(10),
        REARLEFT(1),

        LEFTENCODER_A(2),
        LEFTENCODER_B(3),
        RIGHTENCODER_A(1),
        RIGHTENCODER_B(0),

        RAMPLEFTMOTOR(-8),
        RAMPRIGHTMOTOR(-9),
        OUTTAKEMOTOR(-11),

        LEDLIGHTSTRIP(-9),
        LEFTLIMITSWITCH(-5),
        RIGHTLIMITSWITCH(-6);

        private final int value;

        PortMap(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
