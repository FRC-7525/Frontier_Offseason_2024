package frc.robot.subsystems.Manager;

import java.io.File;
import org.team7525.subsystem.Subsystem;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import frc.robot.Constants;
import frc.robot.subsystems.AutoAligner.AutoAligner;
import frc.robot.subsystems.Drive.Drive;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;

public class Manager extends Subsystem<ManagerStates> {
    SwerveDrive swerveDrive;
    AutoAligner autoAligner;
    Drive drive;

    public Manager() {
        super("Manager", ManagerStates.MANUAL);

        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
        
        // Sim SwerveDrive Configs:
        try {
            File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(), "swerve");
            this.swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(Constants.Drive.Sim.MAX_SPEED, new Pose2d(new Translation2d(6, 6), Rotation2d.fromDegrees(0)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SwerveDrive", e);
        }
        swerveDrive.setHeadingCorrection(false);
        swerveDrive.setCosineCompensator(false);

        this.autoAligner = new AutoAligner(swerveDrive);
        this.drive = new Drive(swerveDrive);
        addTriggers();
    }

    @Override
    public void runState() {
        autoAligner.setState(getState().getAutoAlignerState());
        drive.setState(getState().getDriveState());

        autoAligner.periodic();
        drive.periodic();

        swerveDrive.updateOdometry();
    }

    private void addTriggers() {
        addTrigger(ManagerStates.MANUAL, ManagerStates.LOCKED, Constants.DRIVER_CONTROLLER::getLeftBumperButtonPressed);
        addTrigger(ManagerStates.LOCKED, ManagerStates.MANUAL, Constants.DRIVER_CONTROLLER::getLeftBumperButtonPressed);
        addTrigger(ManagerStates.AUTO_ALIGNING_REEF, ManagerStates.MANUAL, Constants.DRIVER_CONTROLLER::getXButtonPressed);
        addTrigger(ManagerStates.AUTO_ALIGNING_FEEDER, ManagerStates.MANUAL, Constants.DRIVER_CONTROLLER::getYButtonPressed);
        addTrigger(ManagerStates.MANUAL, ManagerStates.AUTO_ALIGNING_REEF, Constants.DRIVER_CONTROLLER::getXButtonPressed);
        addTrigger(ManagerStates.MANUAL, ManagerStates.AUTO_ALIGNING_FEEDER, Constants.DRIVER_CONTROLLER::getYButtonPressed);
        addTrigger(ManagerStates.AUTO_ALIGNING_REEF, ManagerStates.MANUAL, autoAligner::atSetPoint);
        addTrigger(ManagerStates.AUTO_ALIGNING_FEEDER, ManagerStates.MANUAL, autoAligner::atSetPoint);
    }
}
