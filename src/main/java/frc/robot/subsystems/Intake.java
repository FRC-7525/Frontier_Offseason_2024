package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        controller = new PIDController(0.17, 0, 0.006); //PID Tune 
        pivotMotor.getEncoder().setPositionConversionFactor(1);
        pivotMotor.getEncoder().setPosition(0); 
    }

    public void setState(IntakeStates state) {
        this.state = state;
    }

    public void periodic() {
        System.out.println(pivotMotor.getEncoder().getPosition());
        SmartDashboard.putData(controller);

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
