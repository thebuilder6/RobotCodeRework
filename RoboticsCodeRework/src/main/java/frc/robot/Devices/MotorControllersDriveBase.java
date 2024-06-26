package frc.robot.Devices;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.Settings;

public class MotorControllersDriveBase implements MotorController {

    MotorController motorControllerPair;
    NeutralMode neutralMode;

    //TODO Investigate motor invertion
    //setInverted(InvertType.FollowMaster)
    //setInverted(InvertType.OpposeMaster)

    public MotorControllersDriveBase(
            int portNumber,
            int followerPortNumber,
            boolean inverted) {
        neutralMode = Settings.getSetting("drivebase_motorcontroller_neutralmode", NeutralMode.class);
        switch (Settings.getSetting("drivebase_motor_controller_type", String.class)) {
            case "VICTORSPX":
                motorControllerPair = initializeDualCANVictorSPX(portNumber, followerPortNumber, inverted);
                break;
            case "TALONSRX":
                motorControllerPair = initializeDualCANTalon(portNumber, followerPortNumber, inverted);
                break;
            default:
                break;
        }
    }

    private MotorController initializeDualCANTalon(
            int portNumber,
            int followerPortNumber,
            boolean inverted) {
        try {
            WPI_TalonSRX leaderMotor = new WPI_TalonSRX(portNumber);
            WPI_TalonSRX followerMotor = new WPI_TalonSRX(followerPortNumber);

            leaderMotor.setNeutralMode(neutralMode);
            followerMotor.setNeutralMode(neutralMode);

            followerMotor.follow(leaderMotor);
            leaderMotor.setInverted(inverted);
            followerMotor.setInverted(inverted);

            return leaderMotor;
        } catch (Exception e) {
            System.err.println("Error: DualTalons Not Activated " + portNumber);
            return null;
        }
    }

    private MotorController initializeDualCANVictorSPX(
            int portNumber,
            int followerPortNumber,
            boolean inverted) {
        try {
            WPI_VictorSPX leaderMotor = new WPI_VictorSPX(portNumber);
            WPI_VictorSPX followerMotor = new WPI_VictorSPX(followerPortNumber);

            leaderMotor.setNeutralMode(neutralMode);
            followerMotor.setNeutralMode(neutralMode);

            followerMotor.follow(leaderMotor);
            leaderMotor.setInverted(inverted);
            followerMotor.setInverted(inverted);
            return leaderMotor;
        } catch (Exception e) {
            System.err.println("Error: DualVictors Not Activated " + portNumber);
            return null;
        }
    }

    @Override
    public void set(double speed) {
        motorControllerPair.set(speed);
    }

    @Override
    public double get() {
        return motorControllerPair.get();
    }

    @Override
    public void setInverted(boolean isInverted) {
        motorControllerPair.setInverted(isInverted);
    }

    @Override
    public boolean getInverted() {
        return motorControllerPair.getInverted();
    }

    @Override
    public void disable() {
        motorControllerPair.disable();
    }

    @Override
    public void stopMotor() {
        motorControllerPair.stopMotor();
    }
}
