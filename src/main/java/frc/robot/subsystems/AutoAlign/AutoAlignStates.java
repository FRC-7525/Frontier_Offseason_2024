package frc.robot.subsystems.AutoAlign;

import org.team7525.subsystem.SubsystemStates;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public enum AutoAlignStates implements SubsystemStates {
    IDLE("IDLE", new Pose2d(new Translation2d(0, 0), new Rotation2d(0))), 
    REEF("REEF", new Pose2d(new Translation2d(5, 6.5), new Rotation2d(235))),
    CORAL("CORAL", new Pose2d(new Translation2d(6, 1), new Rotation2d(0))); 
    
    private String stateString; 
    private Pose2d targetPose;

    AutoAlignStates(String stateString, Pose2d targetPose) {
        this.stateString = stateString;
        this.targetPose = targetPose;
    }

    public String getStateString() {
        return stateString; 
    }

    public Pose2d getTargetPose() {
        return targetPose; 
    }
    
}
