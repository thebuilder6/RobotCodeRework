package frc.robot.RobotDefinitions;

import java.util.HashMap;

import frc.robot.Settings;
import frc.robot.Subsystems.DriveBase;
import frc.robot.Subsystems.SmartDashBoardIO;

public class GyroBot implements RobotDefinition
{

    HashMap<String, String> settings = new HashMap<String, String>();

    public enum BotSettings
    {
        NUMBEROFCONTROLLERS("1"),
        GYRO("enabled");

        String value;

        BotSettings(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }

    }

    public boolean transferSettings()
    {
        for (BotSettings botSetting : BotSettings.values())
        {
            settings.put(botSetting.name(), botSetting.value);
        }
        Settings.appendBotSettings(settings);
        for (PortMap portMap : PortMap.values())
        {
            portList.put(portMap.name(), portMap.value);
        }
        Settings.appendPortMap(portList);
        return true;
    }

    public boolean initalizeSubsystems()
    {
        transferSettings();

        SmartDashBoardIO.getInstance();
        DriveBase.getInstance();
        // Add subsystems here
        return true;
    }

    HashMap<String, Integer> portList = new HashMap<String, Integer>();

    public enum PortMap
    {
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

        PortMap(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return value;
        }
    }
}
