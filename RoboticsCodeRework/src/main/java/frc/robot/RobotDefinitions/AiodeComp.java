package frc.robot.RobotDefinitions;

import frc.robot.Subsystems.DriveBase;

public class AiodeComp implements RobotDefinition
{

    public boolean initializeSubsystems()
    {
        // Add subsystems here
        DriveBase.getInstance();
        return true;
    }

    public class PortMap
    {
        public static final int XBOX_DRIVER_CONTROLLER = 0;
        public static final int XBOX_OPERATOR_CONTROLLER = 1;

        public static final int CLIMBERMOTORLEFT = -4;
        public static final int CLIMBERMOTORRIGHT = -5;

        public static final int CLIMBERLEFTENCODER_A = -1;
        public static final int CLIMBERLEFTENCODER_B = -1;
        public static final int CLIMBERRIGHTENCODER_A = -1;
        public static final int CLIMBERRIGHTENCODER_B = -1;

        public static final int INTAKEMOTORPIVOT = -1;
        public static final int INTAKEMOTORROLLER = -1;
        public static final int INTAKEPIVOTENCODER = -1;
        public static final int INTAKELIMITSWITCH = -1;

        public static final int FRONTRIGHT = 3;
        public static final int REARRIGHT = 2;
        public static final int FRONTLEFT = 10;
        public static final int REARLEFT = 1;

        public static final int LEFTENCODER_A = 2;
        public static final int LEFTENCODER_B = 3;
        public static final int RIGHTENCODER_A = 1;
        public static final int RIGHTENCODER_B = 0;

        public static final int RAMPLEFTMOTOR = -8;
        public static final int RAMPRIGHTMOTOR = -9;
        public static final int OUTTAKEMOTOR = -11;

        public static final int LEDLIGHTSTRIP = -9;
        public static final int LEFTLIMITSWITCH = -5;
        public static final int RIGHTLIMITSWITCH = -6;
    }

}