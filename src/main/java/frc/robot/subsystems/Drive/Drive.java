package frc.robot.subsystems.Drive;

import org.team7525.subsystem.Subsystem;

import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.Constants;
import swervelib.SwerveDrive;

public class Drive extends Subsystem<DriveStates> {
    SwerveDrive swerveDrive;

    public Drive(SwerveDrive swerveDrive) {
        super("Drive", DriveStates.MANUAL);

        this.swerveDrive = swerveDrive;
    }

    @Override
    public void runState(){
        switch (getState()) {
            case AUTO_ALIGNING:
                // DO NOTHING!
                break;
            case MANUAL:
                swerveDrive.drive(new Translation2d(Constants.DRIVER_CONTROLLER.getLeftX() * Constants.Drive.Sim.MAX_SPEED, -1 * Constants.DRIVER_CONTROLLER.getLeftY() * Constants.Drive.Sim.MAX_SPEED), Constants.DRIVER_CONTROLLER.getRightX(), true, false);
                break;
            case LOCKED:
                swerveDrive.lockPose();
                break;
        }
    }
    
}
