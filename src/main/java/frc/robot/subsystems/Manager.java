package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Intake.IntakeStates;

public class Manager {
    private Shooter shooter; 
    private Intake intake;
    private ManagerState robotState; 
    private XboxController controller; 

    
    enum ManagerState{
        IDLE, 
        INTAKING,  
        SHOOTING
    }

    Manager() {
        intake  = new Intake(); 
        shooter = new Shooter();
        robotState = ManagerState.IDLE; 
    }

    public void periodic() {
        switch (robotState) {
            case IDLE:
                shooter.setState(ShooterStates.IDLE);
                intake.setState(IntakeStates.IDLE);
                if (controller.getBButtonPressed()) {
                    robotState = ManagerState.INTAKING; 
                }
                else if (controller.getYButtonPressed()) {
                    robotState= ManagerState.SHOOTING; 
                }
                break;
            case INTAKING: 
                intake.setState(IntakeStates.INTAKING);
                if (controller.getBButtonPressed()) {
                    robotState = ManagerState.IDLE; 
                }
                break; 
            case SHOOTING:
                shooter.setState(ShooterStates.SHOOTING); 
                if (controller.getYButtonPressed()) {
                    robotState = ManagerState.IDLE; 
                }
                break; 
            default:
                break;
        }
    }
}
