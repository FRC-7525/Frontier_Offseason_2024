package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants;


enum AmpStates {
    OUT,
    IN 
}  

public class AmpBar {
    private AmpStates state;
    TalonFX rotationalMotor;
    CANSparkMax lPivotMotor;
    CANSparkMax rPivotMotor;
    PIDController controller; 

    public AmpBar() {
        rotationalMotor = new TalonFX(Constants.AmpBar.ROTATIONAL_MOTOR_ID);
        lPivotMotor = new CANSparkMax(Constants.AmpBar.LPIVOT_MOTOR_ID, MotorType.kBrushless); // TODO Check this
        rPivotMotor = new CANSparkMax(Constants.AmpBar.RPIVOT_MOTOR_ID, MotorType.kBrushless);
        controller = new PIDController(0.1, 0, 0); //TODO PID Tune 
        state = AmpStates.IN;

    }
    public void setState(AmpStates state) { 
        this.state = state;
    }
    public void periodic() {
        switch (state) {
            case IN:
                lPivotMotor.set(controller.calculate(lPivotMotor.getEncoder().getPosition(), Constants.AmpBar.IN));
                rPivotMotor.set(controller.calculate(rPivotMotor.getEncoder().getPosition(), Constants.AmpBar.IN));
                rotationalMotor.set(Constants.AmpBar.IDLE_SPEED);           
                break;
            case OUT:
                lPivotMotor.set(controller.calculate(lPivotMotor.getEncoder().getPosition(), Constants.AmpBar.OUT));
                rPivotMotor.set(controller.calculate(rPivotMotor.getEncoder().getPosition(), Constants.AmpBar.OUT));
                rotationalMotor.set(Constants.AmpBar.DEPLOYED_SPEED);
                break;
            default:
                
                break;
        }
    }
}