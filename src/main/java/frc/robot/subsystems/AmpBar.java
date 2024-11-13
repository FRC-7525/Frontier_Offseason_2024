package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;

import frc.robot.Constants;

enum Ampstates {
    OUT,
    IN 
}  

public class AmpBar {
    TalonFX rotationalMotor;
    CANSparkMax lPivotMotor;
    CANSparkMax rPivotMotor;

    public AmpBar() {
        rotationalMotor = new TalonFX(Constants.AmpBar.ROTATIONAL_MOTOR_ID);
        //lPivotMotor = new CANSparkMax();
        //rPivotMotor = new CANSparkMax();
    }

     

    
}
