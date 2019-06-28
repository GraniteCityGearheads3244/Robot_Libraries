/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3244.MecanumTest.WPI_Classes;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class TalonSRX_Driver {

    private static int m_kPIDLoopIdx = 0;
    private static int m_kTimeoutMs = 30;

    public static WPI_TalonSRX TalonConfigure_With_Encoder(WPI_TalonSRX _talon, FeedbackDevice _FeedbackDevice, 
                                        double kP, double kI, double kD, double kF, boolean _inverMotor, boolean _invertSensor){
         /* Factory Default all hardware to prevent unexpected behaviour */
         _talon.configFactoryDefault();

         
        /* Config sensor used for Primary PID [Velocity] */
         _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, m_kPIDLoopIdx, m_kTimeoutMs);
 
         /**
          * Invert Motor so green LED flash when sent a positive command and spins in a positive direction
          */
         _talon.setInverted(_inverMotor);

         /**
          * Phase sensor accordingly. 
          * Positive Sensor Reading should match Green (blinking) Leds on Talon
          */
         _talon.setSensorPhase(_invertSensor);
 
         /* Config the peak and nominal outputs */
         _talon.configNominalOutputForward(0, m_kTimeoutMs);
         _talon.configNominalOutputReverse(0, m_kTimeoutMs);
         _talon.configPeakOutputForward(1, m_kTimeoutMs);
         _talon.configPeakOutputReverse(-1, m_kTimeoutMs);
 
         /* Config the Velocity closed loop gains in slot0 */
         _talon.config_kF(m_kPIDLoopIdx, kF, m_kTimeoutMs);
         _talon.config_kP(m_kPIDLoopIdx, kP, m_kTimeoutMs);
         _talon.config_kI(m_kPIDLoopIdx, kI, m_kTimeoutMs);
         _talon.config_kD(m_kPIDLoopIdx, kD, m_kTimeoutMs);

         return _talon;
     
    }
}
