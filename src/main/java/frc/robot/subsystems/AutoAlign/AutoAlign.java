package frc.robot.subsystems.AutoAlign;

import org.team7525.subsystem.Subsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import swervelib.SwerveDrive;

public class AutoAlign extends Subsystem<AutoAlignStates> {
    private PIDController translationPIDController;
    private PIDController rotationPIDController;
    private SwerveDrive swerveDrive; 

    public AutoAlign(SwerveDrive swerveDrive) {
        super("AutoAlign", AutoAlignStates.IDLE);

        this.swerveDrive = swerveDrive;

        translationPIDController = new PIDController(0.7, 0, 0.1);
        rotationPIDController = new PIDController(0.7, 0, 0.1);

        translationPIDController.setTolerance(0.1);
        rotationPIDController.setTolerance(3);
    }

    public void driveToPosition(Pose2d pose) {
        double x = translationPIDController.calculate(swerveDrive.getPose().getX(), pose.getX());
        double y = translationPIDController.calculate(swerveDrive.getPose().getY(), pose.getY());
        double angle = rotationPIDController.calculate(swerveDrive.getPose().getRotation().getDegrees(), pose.getRotation().getDegrees());

        swerveDrive.drive(new Translation2d(x, y), angle, true, false);
    }

    @Override
    public void runState() {
        if (getState() == AutoAlignStates.IDLE) {
            //DO NOTHING
        } else {
            driveToPosition(getState().getTargetPose());
        }
    }
    
}
