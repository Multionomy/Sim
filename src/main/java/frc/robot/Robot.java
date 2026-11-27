// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  public final RobotContainer m_robotContainer;

  private final DriveSubsystem DriveSub = new DriveSubsystem();

  @Override
  public void robotInit() {
    DriveSub.initializeMotors();
  }




  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      CommandScheduler.getInstance().schedule(m_autonomousCommand);
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double speed = -MathUtil.applyDeadband(DriveSub.m_driverController.getLeftY(), 0.2); 
    double speed1 = -MathUtil.applyDeadband(DriveSub.m_driverController.getRightX(), 0.2); 
    double speed2 = -MathUtil.applyDeadband(DriveSub.m_driverController.getLeftY(), 0.2); 
    double speed3 = -MathUtil.applyDeadband(DriveSub.m_driverController.getRightX(), 0.2); 
    double speed4 = -MathUtil.applyDeadband(DriveSub.m_driverController.getLeftY(), 0.2); 
    double speed5 = -MathUtil.applyDeadband(DriveSub.m_driverController.getRightX(), 0.2); 
    double speed6 = -MathUtil.applyDeadband(DriveSub.m_driverController.getLeftY(), 0.2); 
    double speed7 = -MathUtil.applyDeadband(DriveSub.m_driverController.getRightX(), 0.2); 
//Sets the joystick input coordinate (X or Y) proportinal to the deadband and stores it in a variable called "speedx"

     speed1 =  DriveSub.m_speedLimiter1.calculate(speed1);
     speed = DriveSub.m_speedLimiter.calculate(speed);
     
     speed3 =  DriveSub.m_speedLimiter3.calculate(speed3);
     speed2 = DriveSub.m_speedLimiter2.calculate(speed2);
    
     speed5 =  DriveSub.m_speedLimiter5.calculate(speed5);
     speed4 = DriveSub.m_speedLimiter4.calculate(speed4);
     
     speed7 =  DriveSub.m_speedLimiter7.calculate(speed7);
     speed6 = DriveSub.m_speedLimiter6.calculate(speed6);
  //Calculated the speed number and changes the value of the original variable

// Top Right Swerve Drive Speed Values
     DriveSub.top_rightX44.setControl(new DutyCycleOut(speed1));
     DriveSub.top_rightX60.setControl(new DutyCycleOut(speed));

// Top Left Swerve Drive Speed Values
     DriveSub.top_leftX44.setControl(new DutyCycleOut(speed3));
     DriveSub.top_leftX60.setControl(new DutyCycleOut(speed2));

// Bottom Right Swerve Drive Speed Values
     DriveSub.bottom_rightX44.setControl(new DutyCycleOut(speed5));
     DriveSub.bottom_rightX60.setControl(new DutyCycleOut(speed4));

// Bottom Left Swerve Drive Speed Values
     DriveSub.bottom_leftX44.setControl(new DutyCycleOut(speed7));
     DriveSub.bottom_leftX60.setControl(new DutyCycleOut(speed6));

     // Gets the current position of the motor at any given moment and stores it in a variable
     double bottomLX44currentPos = DriveSub.bottom_leftX44.getPosition().getValueAsDouble();
     double bottomRX44currentPos = DriveSub.bottom_rightX44.getPosition().getValueAsDouble();
     double topLX44currentPos = DriveSub.top_leftX44.getPosition().getValueAsDouble();
     double topRX44currentPos = DriveSub.top_rightX44.getPosition().getValueAsDouble();

     double topLX44Delta_r = topLX44currentPos - DriveSub.topLX44InitPos;
     double topRX44Delta_r = topRX44currentPos - DriveSub.topRX44InitPos;
     double bottomLX44Delta_r = bottomLX44currentPos - DriveSub.bottomLX44InitPos;
     double bottomRX44Delta_r = bottomRX44currentPos - DriveSub.bottomRX44InitPos;

  }
   
    
  


  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
