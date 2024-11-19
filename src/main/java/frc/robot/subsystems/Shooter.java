package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.BangBangController;
import frc.robot.Constants;

enum ShooterStates {
    IDLE, 
    SHOOTING,
    SLOW
}

public class Shooter {
    
    private ShooterStates state;  
    private TalonFX leftMotor; 
    private TalonFX rightMotor;
    private BangBangController bangBangController;

    public Shooter() {
        state = ShooterStates.IDLE;
        bangBangController = new BangBangController();
        leftMotor = new TalonFX(Constants.Shooter.LEFT_MOTOR_ID);
        rightMotor = new TalonFX(Constants.Shooter.RIGHT_MOTOR_ID);
    }

    public void setState(ShooterStates state) {
        this.state = state;
    }

    public void periodic() {
        switch (state) {
            case IDLE:
                leftMotor.set(0);
                rightMotor.set(0);
                break; 
            case SHOOTING: 
                leftMotor.set(bangBangController.calculate(leftMotor.getVelocity().getValueAsDouble(), Constants.Shooter.SHOOTING_RPS));
                rightMotor.set(bangBangController.calculate(rightMotor.getVelocity().getValueAsDouble(), Constants.Shooter.SHOOTING_RPS));
            case SLOW:
                leftMotor.set(bangBangController.calculate(leftMotor.getVelocity().getValueAsDouble(), Constants.Shooter.SLOW_RPS));
                rightMotor.set(bangBangController.calculate(rightMotor.getVelocity().getValueAsDouble(), Constants.Shooter.SLOW_RPS));
        }
    }
}
