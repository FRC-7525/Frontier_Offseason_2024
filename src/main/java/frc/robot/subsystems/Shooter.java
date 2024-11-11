package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.BangBangController;
import frc.robot.Constants;

enum ShooterStates {
    IDLE, 
    SHOOTING
}

public class Shooter {
    
    private ShooterStates state;  
    private TalonFX leftMotor; 
    private TalonFX rightMotor;
    private BangBangController motorController;
    private double speed; 


    public Shooter() {
        state = ShooterStates.IDLE;
        motorController = new BangBangController();
        leftMotor = new TalonFX(15);
        rightMotor = new TalonFX(14);
        speed = Constants.Shooter.speed;
    }

    public void setState(ShooterStates state) {
        this.state = state;
    }

    public void periodic() {
        if (state == ShooterStates.IDLE) {
            leftMotor.set(0);
            rightMotor.set(0); 
        } 
        else if (state == ShooterStates.SHOOTING) {

            leftMotor.set(motorController.calculate(leftMotor.getVelocity().getValueAsDouble(), speed));
            rightMotor.set(motorController.calculate(rightMotor.getVelocity().getValueAsDouble(), speed));


        } 
    }
}
