package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants;

public class Intake {
    private IntakeStates state;  
    private CANSparkMax pivotMotor; 
    private TalonFX rotationalMotor; 
    private PIDController controller;

    enum IntakeStates {
        IDLE, 
        INTAKING,
        SLOW,
        PASSING
    }

    public Intake() {
        state = IntakeStates.IDLE;
        pivotMotor = new CANSparkMax(Constants.Intake.PIVOT_MOTOR_ID, MotorType.kBrushless); 
        rotationalMotor = new TalonFX(Constants.Intake.ROTATIONAL_MOTOR_ID);
        
        controller = new PIDController(0.2, 0, 0); //PID Tune 
        pivotMotor.getEncoder().setPosition(0);
    }

    public void setState(IntakeStates state) {
        this.state = state;
    }

    public void periodic() {
        
        switch (state) {
            case IDLE:  
                pivotMotor.set(controller.calculate(pivotMotor.getEncoder().getPosition(), Constants.Intake.IN));
                rotationalMotor.set(Constants.Intake.IDLE_SPEED); 
                break; 
            case PASSING:
                pivotMotor.set(controller.calculate(pivotMotor.getEncoder().getPosition(), Constants.Intake.IN));
                rotationalMotor.set(Constants.Intake.PASSING_SPEED);
            case INTAKING:
                pivotMotor.set(controller.calculate(pivotMotor.getEncoder().getPosition(), Constants.Intake.OUT));
                rotationalMotor.set(Constants.Intake.INTAKING_SPEED);
                break;
            case SLOW:
                pivotMotor.set(controller.calculate(pivotMotor.getEncoder().getPosition(), Constants.Intake.IN));
                rotationalMotor.set(Constants.Intake.SLOW);
                
        }
    }
 }   
