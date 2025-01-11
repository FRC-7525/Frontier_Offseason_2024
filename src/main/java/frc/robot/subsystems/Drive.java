package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;

import org.team7525.subsystem.Subsystem;

import edu.wpi.first.wpilibj.Filesystem;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import edu.wpi.first.math.geometry.Translation2d;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;
import frc.robot.Constants;
import frc.robot.subsystems.AutoAlign.AutoAlign;
import frc.robot.subsystems.AutoAlign.AutoAlignStates;


public class Drive extends Subsystem<AutoAlignStates> {
    private double MAXIMUM_SPEED;
    private File swerveJsonDirectory;
    private SwerveDrive swerveDrive;
    private AutoAlign autoAligner;
    public Drive() {
        super("Drive", AutoAlignStates.IDLE);
        MAXIMUM_SPEED = Constants.Drive.MAXIMUM_SPEED;

        swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");
        try {
            swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(MAXIMUM_SPEED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;

        autoAligner = new AutoAlign(swerveDrive);       
    }
    
    @Override
    public void runState() {
        if (autoAligner.getState() == AutoAlignStates.IDLE) {
            swerveDrive.drive(new Translation2d(Constants.CONTROLLER.getLeftX() * MAXIMUM_SPEED,
                Constants.CONTROLLER.getLeftY() * MAXIMUM_SPEED),
                Constants.CONTROLLER.getRightX() * MAXIMUM_SPEED, 
                    true, false);
        } else {
            autoAligner.runState();
        }
        

    }

}