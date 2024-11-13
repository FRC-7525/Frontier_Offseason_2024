package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;

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

    public AmpBar() {
        rotationalMotor = new TalonFX(Constants.AmpBar.ROTATIONAL_MOTOR_ID);
        lPivotMotor = new CANSparkMax(Constants.AmpBar.LPIVOT_MOTOR_ID);
        rPivotMotor = new CANSparkMax(Constants.AmpBar.RPIVOT_MOTOR_ID);
        state = AmpStates.IN;
    }
    public void setState(AmpStates state) { 
        this.state = state;
    }
    public void periodic() {
        if (state == AmpStates.IN) {
            lPivotMotor.set(0);
            rPivotMotor.set(0);
            rotationalMotor.set(0);
        }
    }

}