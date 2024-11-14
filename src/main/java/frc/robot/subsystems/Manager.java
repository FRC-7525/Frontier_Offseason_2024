package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Intake.IntakeStates;

public class Manager {
    private Shooter shooter; 
    private Intake intake;
    private AmpBar ampBar; 
    private ManagerState robotState; 
    private XboxController controller; 

    enum ManagerState{
        IDLE, 
        INTAKING,  
        SHOOTING,
        FEEDING
    }

    public Manager() {
        intake  = new Intake(); 
        shooter = new Shooter();
        robotState = ManagerState.IDLE; 
        ampBar = new AmpBar();  
    }

    public void periodic() {
        ampBar.periodic();
        shooter.periodic();
        intake.periodic();
        
        switch (robotState) {
            case IDLE:
                shooter.setState(ShooterStates.IDLE);
                intake.setState(IntakeStates.IDLE);
                ampBar.setState(AmpStates.IN);
                if (controller.getBButtonPressed()) {
                    robotState = ManagerState.INTAKING; 
                }
                else if (controller.getYButtonPressed()) {
                    robotState = ManagerState.SHOOTING; 
                }
                else if (controller.getRightBumperPressed()) {
                    robotState = ManagerState.FEEDING;
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
            case FEEDING:
                ampBar.setState(AmpStates.OUT);
                if (controller.getRightBumperPressed()) {
                    robotState = ManagerState.IDLE;
                }
                else if (controller.getYButtonPressed()) {
                    robotState = ManagerState.SHOOTING;
                }
                break;
            default:
                break;
        }
    
    }
}
