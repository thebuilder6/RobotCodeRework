package frc.robot.Devices;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.Settings;

public class DrivebaseMotorControllers implements MotorController {

    MotorController motorControllerPair;

    //TODO Investigate motor invertion
    //setInverted(InvertType.FollowMaster)
    //setInverted(InvertType.OpposeMaster)

    public DrivebaseMotorControllers(
            int portNumber,
            int followerPortNumber,
            boolean inverted) {
        switch (Settings.getSetting("DRIVEBASE.MOTORCONTROLLER.TYPE", String.class)) {
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

            leaderMotor.setNeutralMode(NeutralMode.Brake);
            followerMotor.setNeutralMode(NeutralMode.Brake);

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

            leaderMotor.setNeutralMode(NeutralMode.Brake);
            followerMotor.setNeutralMode(NeutralMode.Brake);

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
