// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Subsystems.Control;
import frc.robot.Subsystems.DriveBase;
import frc.robot.Subsystems.IMU;
import frc.robot.Subsystems.SmartDashBoardIO;
import frc.robot.Subsystems.SubsystemManager;

import java.util.HashMap;
import java.util.Map;
import frc.robot.Settings;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Auto.AutoMissionExecutor;
import frc.robot.Auto.AutoMissionChooser;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  private AutoMissionExecutor autoMissionExecutor = new AutoMissionExecutor();
  private AutoMissionChooser autoMissionChooser = new AutoMissionChooser();

  private Map<String, String> robotMap = new HashMap<>();
  // robotMemoryName = robot on board memory name
  public String defaultRobotName = "GyroBot";
  public String robotMemoryName;

  @Override
  public void robotInit() {
    robotMap.put("GyroBot", "gyrobot.properties");
    robotMap.put("AiodeComp", "aiodecomp.properties");
    robotMap.put("SimulatorBot", "simulatorbot.properties");
    Settings.loadSettings();

    robotMemoryName = Preferences.getString("ROBOT_NAME", defaultRobotName);
    Settings.loadBotSettings(robotMap.get(robotMemoryName));
    SmartDashboard.putString("Selected Bot", robotMemoryName);

    DriveBase.getInstance();
    SmartDashBoardIO.getInstance();
    Control control = Control.getInstance();
    IMU imu = IMU.getInstance();

    SubsystemManager.initializeAllSubsystems();
  }

  @Override
  public void robotPeriodic() {
    SubsystemManager.updateAllSubsystems();
    SubsystemManager.logAllSubsystems();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {
  }
}
