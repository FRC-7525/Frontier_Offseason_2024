package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants;

public class Intake {
    private IntakeStates state;  
    private CANSparkMax intake; 
    private TalonFX wheels; 
    private PIDController controller;

    enum IntakeStates {
        IDLE, 
        INTAKING
    }

    public Intake() {
        state = IntakeStates.IDLE;
        intake = new CANSparkMax(20, MotorType.kBrushless); 
        
        wheels = new TalonFX(32);
        
        controller = new PIDController(0.1, 0, 0); //PID Tune 
        intake.getEncoder().setPosition(0);
    }

    public void setState(IntakeStates state) {
        this.state = state;
    }

    public void periodic() {
        
        switch (state) {
            case IDLE:  
                intake.set(controller.calculate(intake.getEncoder().getPosition(), Constants.Intake.in));
                wheels.set(0); 
                break; 
           
            case INTAKING:
                intake.set(controller.calculate(intake.getEncoder().getPosition(), Constants.Intake.out));
                wheels.set(Constants.Intake.speed);
                break; 
        }
    }
 }   
