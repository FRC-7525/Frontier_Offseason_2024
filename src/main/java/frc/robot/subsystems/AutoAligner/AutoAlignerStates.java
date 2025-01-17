package frc.robot.subsystems.AutoAligner;

import java.util.List;
import org.team7525.subsystem.SubsystemStates;
import edu.wpi.first.math.geometry.Pose2d;
import frc.robot.Constants;

public enum AutoAlignerStates implements SubsystemStates {
    OFF("OFF", null),
    NEAREST_FEEDER("NEAREST_FEEDER", Constants.AutoAligner.NEAREST_FEEDER),
    NEAREST_REEF("NEAREST_REEF", Constants.AutoAligner.NEAREST_REEF);

    private final String stateString;
    private final List<Pose2d> targetPoses;

    AutoAlignerStates(String stateString, List<Pose2d> targetPoses) {
        this.stateString = stateString;
        this.targetPoses = targetPoses;
    }

    public List<Pose2d> getTargetPoses() {
        return targetPoses;
    }

    @Override
    public String toString() {
        return stateString;
    }
    
}
