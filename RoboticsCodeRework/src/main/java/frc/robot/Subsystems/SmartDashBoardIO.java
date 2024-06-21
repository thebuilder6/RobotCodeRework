package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashBoardIO implements Subsystem
{

    private static SmartDashBoardIO instance = null;

    public static SmartDashBoardIO getInstance()
    {
        if (instance == null)
        {
            instance = new SmartDashBoardIO();
        }
        return instance;
    }

    private SmartDashBoardIO()
    {
        SubsystemManager.registerSubsystem(this);
        initialize();
    }

    @Override
    public String getName()
    {
        return "SmartDashBoardIO";
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

    private void initialize()
    {
        SmartDashboard.putNumber("Input1", 0);
        SmartDashboard.putNumber("Input2", 0);
        SmartDashboard.putNumber("output", 0);
        SmartDashboard.putBoolean("calculate", false);
    }

    private void calculator()
    {
        double a = SmartDashboard.getNumber("Input1", 0);
        double b = SmartDashboard.getNumber("Input2", 0);
        if (SmartDashboard.getBoolean("calculate", true))
        {
            SmartDashboard.putNumber("output", a + b);
            SmartDashboard.putBoolean("calculate", false);
        }

    }

    private void subsystemStatus()
    {
        SmartDashboard.put
        for (Subsystem subsystem : SubsystemManager.getSubsystems())
        {
            SmartDashboard.putBooleanArray(subsystem.getName(), subsystem.go());
        }
    }

    @Override
    public void update()
    {
        calculator();

    }
}
