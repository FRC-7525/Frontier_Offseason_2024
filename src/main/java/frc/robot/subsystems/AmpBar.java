package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
// import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;


enum AmpStates {
    OUT,
    FEEDING,
    SHOOTING,
    IN 
}  

public class AmpBar {
    private AmpStates state;
    private TalonFX rotationalMotor;
    private CANSparkMax lPivotMotor;
    private CANSparkMax rPivotMotor;
    private PIDController controller; 
    private final DigitalInput beamBreak = new DigitalInput(9);

    public AmpBar() {
        rotationalMotor = new TalonFX(Constants.AmpBar.ROTATIONAL_MOTOR_ID);
        lPivotMotor = new CANSparkMax(Constants.AmpBar.LPIVOT_MOTOR_ID, MotorType.kBrushless);
        rPivotMotor = new CANSparkMax(Constants.AmpBar.RPIVOT_MOTOR_ID, MotorType.kBrushless);
        controller = new PIDController(0.1, 0, 0); //TODO PID Tune 
        

        lPivotMotor.getEncoder().setPosition(1);
        rPivotMotor.getEncoder().setPosition(0);
        lPivotMotor.getEncoder().setPosition(0);

        rPivotMotor.follow(lPivotMotor, true);
        lPivotMotor.setIdleMode(IdleMode.kCoast);
		rPivotMotor.setIdleMode(IdleMode.kCoast);
        
        state = AmpStates.IN;
    }

    public void setState(AmpStates state) { 
        this.state = state;
    }

    public boolean hasNote() {
        return !beamBreak.get();
    }

    public void periodic() {
        switch (state) {
            case IN:
                lPivotMotor.set(controller.calculate(lPivotMotor.getEncoder().getPosition(), Constants.AmpBar.IN));
                rotationalMotor.set(Constants.AmpBar.IDLE_SPEED);           
                break;
            case OUT:
                lPivotMotor.set(controller.calculate(lPivotMotor.getEncoder().getPosition(), Constants.AmpBar.OUT));
                rotationalMotor.set(Constants.AmpBar.IDLE_SPEED);
                break;
            case FEEDING:
                lPivotMotor.set(controller.calculate(lPivotMotor.getEncoder().getPosition(), Constants.AmpBar.OUT));
                rotationalMotor.set(Constants.AmpBar.FEEDING_SPEED);
            case SHOOTING:
                lPivotMotor.set(controller.calculate(lPivotMotor.getEncoder().getPosition(), Constants.AmpBar.OUT));
                rotationalMotor.set(Constants.AmpBar.SHOOTING_SPEED);
            default:
                break;
        }
    }
}