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
  
  public final TalonFX m_getLeftDrive = new TalonFX(0);
  public final TalonFX m_getRightDrive = new TalonFX(1);


  public final CommandXboxController m_driverController = new CommandXboxController(0);
  public final SlewRateLimiter m_speedLimiter = new SlewRateLimiter(0.25);
  public final SlewRateLimiter m_speedLimiter1 = new SlewRateLimiter(0.25);


    public void robotIn() {
      

    m_getLeftDrive.getConfigurator().apply(new TalonFXConfiguration());
    // Motor gets reset to factory config

    // Motor current configs
    var currentConfiguration = new CurrentLimitsConfigs();
    currentConfiguration.StatorCurrentLimit = 80;
    currentConfiguration.StatorCurrentLimitEnable = true;

    // Refreshes and applies new current configs
    m_getLeftDrive.getConfigurator().refresh(currentConfiguration);
    m_getLeftDrive.getConfigurator().apply(currentConfiguration);

    // For the other motor on the same drive (X60)

    m_getRightDrive.getConfigurator().apply(new TalonFXConfiguration());
    
    currentConfiguration.StatorCurrentLimit = 80;
    currentConfiguration.StatorCurrentLimitEnable = true;

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
