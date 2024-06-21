package frc.robot.Subsystems;

public class DriveBase implements Subsystem
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

    @Override
    public String getName()
    {
        return "DriveBase";
    }

    @Override
    public boolean go()
    {
        return true;
    }

    @Override
    public void log()
    {

    }

    @Override
    public void update()
    {

    }

}
