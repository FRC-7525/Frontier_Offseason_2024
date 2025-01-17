package frc.robot.subsystems.Manager;

import org.team7525.subsystem.SubsystemStates;
import frc.robot.subsystems.AutoAligner.AutoAlignerStates;
import frc.robot.subsystems.Drive.DriveStates;

public enum ManagerStates implements SubsystemStates {
    AUTO_ALIGNING_REEF("AUTO_ALIGNING_REEF", AutoAlignerStates.NEAREST_REEF, DriveStates.AUTO_ALIGNING),
    AUTO_ALIGNING_FEEDER("AUTO_ALIGNING_FEEDER", AutoAlignerStates.NEAREST_FEEDER,  DriveStates.AUTO_ALIGNING),
    MANUAL("MANUAL", AutoAlignerStates.OFF, DriveStates.MANUAL),
    LOCKED("LOCKED", AutoAlignerStates.OFF, DriveStates.LOCKED);
    
    private final String stateString;
    private final AutoAlignerStates autoAlignerState;
    private final DriveStates driveState;


    ManagerStates(String stateString, AutoAlignerStates autoAlignerState, DriveStates driveState) {
        this.stateString = stateString;
        this.autoAlignerState = autoAlignerState;
        this.driveState = driveState;
    }

    @Override
    public String getStateString() {
        return stateString;
    }


    public AutoAlignerStates getAutoAlignerState() {
        return autoAlignerState;
    }

    public DriveStates getDriveState() {
        return driveState;
    }
}
