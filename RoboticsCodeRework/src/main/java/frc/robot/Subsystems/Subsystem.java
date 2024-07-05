package frc.robot.Subsystems;

public interface Subsystem
{

    public String getName();

    public boolean isActive();

    public void update();

    public void initialize();

    public void log();
}