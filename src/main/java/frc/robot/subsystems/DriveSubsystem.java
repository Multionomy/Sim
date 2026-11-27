// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;;

public class DriveSubsystem extends SubsystemBase {
  
  // Top Right Swerve Motors
  public final TalonFX top_rightX60 = new TalonFX(3);
  public final TalonFX top_rightX44 = new TalonFX(1);

  // Top Left Swerve Motors
  public final TalonFX top_leftX60 = new TalonFX(0);
  public final TalonFX top_leftX44 = new TalonFX(4);

  // Bottom Right Swerve Motors
  public final TalonFX bottom_rightX60 = new TalonFX(6);
  public final TalonFX bottom_rightX44 = new TalonFX(7);

  //Bottom Left Swerve Motors
  public final TalonFX bottom_leftX60 = new TalonFX(5);
  public final TalonFX bottom_leftX44 = new TalonFX(2);



  public final CommandXboxController m_driverController = new CommandXboxController(0);
  public final SlewRateLimiter m_speedLimiter = new SlewRateLimiter(0.25);
  public final SlewRateLimiter m_speedLimiter1 = new SlewRateLimiter(0.25);
  public final SlewRateLimiter m_speedLimiter2 = new SlewRateLimiter(0.25);
  public final SlewRateLimiter m_speedLimiter3 = new SlewRateLimiter(0.25);
  public final SlewRateLimiter m_speedLimiter4 = new SlewRateLimiter(0.25);
  public final SlewRateLimiter m_speedLimiter5 = new SlewRateLimiter(0.25);
  public final SlewRateLimiter m_speedLimiter6 = new SlewRateLimiter(0.25);
  public final SlewRateLimiter m_speedLimiter7 = new SlewRateLimiter(0.25);

  // Gets the inital rotation of the X44's and stores them in a variable
    public double bottomLX44InitPos;
    public double bottomRX44InitPos;
    public double topLX44InitPos;
    public double topRX44InitPos;


    public void initializeMotors() {
      

    top_rightX60.getConfigurator().apply(new TalonFXConfiguration());
    top_rightX44.getConfigurator().apply(new TalonFXConfiguration());
    
    top_leftX60.getConfigurator().apply(new TalonFXConfiguration());
    top_leftX44.getConfigurator().apply(new TalonFXConfiguration());

    bottom_rightX60.getConfigurator().apply(new TalonFXConfiguration());
    bottom_rightX44.getConfigurator().apply(new TalonFXConfiguration());

    bottom_leftX60.getConfigurator().apply(new TalonFXConfiguration());
    bottom_leftX44.getConfigurator().apply(new TalonFXConfiguration());
    // Motor gets reset to factory config

    // Motor current configs
    var currentConfiguration = new CurrentLimitsConfigs();
    currentConfiguration.StatorCurrentLimit = 80;
    currentConfiguration.StatorCurrentLimitEnable = true;

    // applies new current configs to all motors
    top_rightX60.getConfigurator().apply(currentConfiguration);
    top_rightX44.getConfigurator().apply(currentConfiguration);

    top_leftX60.getConfigurator().apply(currentConfiguration);
    top_leftX44.getConfigurator().apply(currentConfiguration);

    bottom_rightX60.getConfigurator().apply(currentConfiguration);
    bottom_rightX44.getConfigurator().apply(currentConfiguration);

    bottom_leftX60.getConfigurator().apply(currentConfiguration);
    bottom_leftX44.getConfigurator().apply(currentConfiguration);

    // defines the values from the doubles aboce this method into this method
    bottomLX44InitPos = bottom_leftX44.getPosition().getValueAsDouble();
    bottomRX44InitPos = bottom_rightX44.getPosition().getValueAsDouble();
    topLX44InitPos = top_leftX44.getPosition().getValueAsDouble();
    topRX44InitPos = top_rightX44.getPosition().getValueAsDouble();

    System.out.println("Bottom Left " + bottomLX44InitPos);
    System.out.println("Bottom Right " + bottomRX44InitPos);
    System.out.println("Top Right " + topRX44InitPos);
    System.out.println("Top Left " + topLX44InitPos);


  }


  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
