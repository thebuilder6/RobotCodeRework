package frc.robot.Subsystems;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Settings;

public class DriveBase implements Subsystem, Sendable
{
    private static DriveBase instance = null;

    public static DriveBase getInstance()
    {
        if (instance == null)
        {
            instance = new DriveBase();
        }
        return instance;
    }

    private DriveBase()
    {
        SubsystemManager.registerSubsystem(this);

    }

    private boolean isActive;

    @Override
    public void initialize()
    {
        if (Settings.getSetting("DRIVEBASE") != null && Settings.getSetting("DRIVEBASE").equals("DISABLE"))
        {
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
                        (Settings.getSetting("PORTMAP.RearRight", Integer.class) < 0))
        {
            isActive = false;
            return;
        }
        else
        {
            switch (Settings.getSetting("MOTORCONTROLLERTYPE", String.class))
            {
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
    public String getName()
    {
        return "DriveBase";
    }

    @Override
    public boolean isActive()
    {
        return isActive;
    }

    @Override
    public void log()
    {
        SmartDashboard.putData(this);
    }

    @Override
    public void initSendable(SendableBuilder builder)
    {
        builder.setSmartDashboardType("DriveBase");
        builder.addBooleanProperty("isActive", this::isActive, isActive -> this.isActive = isActive);
        builder.addStringArrayProperty("Ports", () -> new String[] { Settings.getSetting("PORTMAP.FrontLeft"), Settings.getSetting("PORTMAP.RearLeft"), Settings.getSetting("PORTMAP.FrontRight"), Settings.getSetting("PORTMAP.RearRight") }, (ports) -> {
        });

    }

    @Override
    public void update()
    {

    }

}
