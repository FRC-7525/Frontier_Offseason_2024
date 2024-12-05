package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Intake.IntakeStates;

public class Manager {
    private Shooter shooter; 
    private Intake intake;
    private AmpBar  ampBar; 
    private ManagerState robotState; 
    private XboxController controller = new XboxController(0); 

    enum ManagerState{
        IDLE, 
        INTAKING,  
        SHOOTING,
        FEEDING,
        HOLDING_NOTE_AMP,
        SHOOTING_AMP
    }

    public Manager() {
        intake  = new Intake(); 
        shooter = new Shooter();
        ampBar = new AmpBar();
        drive = new Drive(); 

        robotState = ManagerState.IDLE; 
    }

    public void periodic() {
        ampBar.periodic();
        shooter.periodic();
        intake.periodic();
        drive.periodic(controller.getLeftX().getAsDouble(), controller.getLeftY().getAsDouble(), controller.getRightX().getAsDouble());
        
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
                else if (controller.getAButtonPressed()) {
                    robotState = ManagerState.FEEDING;
                }
                break;
            case INTAKING: 
                intake.setState(IntakeStates.INTAKING);
                ampBar.setState(AmpStates.IN);
                shooter.setState(ShooterStates.IDLE);
                if (controller.getBButtonPressed()) {
                    robotState = ManagerState.IDLE; 
                }
                break; 
            case SHOOTING:
                shooter.setState(ShooterStates.SHOOTING); 
                intake.setState(IntakeStates.PASSING);
                ampBar.setState(AmpStates.IN);
                if (controller.getYButtonPressed()) {
                    robotState = ManagerState.IDLE; 
                }
                break; 
            case FEEDING:
                ampBar.setState(AmpStates.FEEDING);
                intake.setState(IntakeStates.SLOW);
                shooter.setState(ShooterStates.SLOW);
                
                if (ampBar.hasNote()) {
                    robotState = ManagerState.HOLDING_NOTE_AMP;
                }
            case HOLDING_NOTE_AMP:
                ampBar.setState(AmpStates.OUT);
                shooter.setState(ShooterStates.IDLE);
                intake.setState(IntakeStates.IDLE);
                
                if (controller.getAButtonPressed()) {
                    robotState = ManagerState.SHOOTING_AMP;
                }
            case SHOOTING_AMP:
                shooter.setState(ShooterStates.IDLE);
                intake.setState(IntakeStates.IDLE);
                ampBar.setState(AmpStates.SHOOTING);

                if (controller.getAButtonPressed()) {
                    robotState = ManagerState.IDLE;
                }

            default:
                break;
        }
    
    }
}
