package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.wpilibj.Filesystem;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import edu.wpi.first.math.geometry.Translation2d;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;
import frc.robot.Constants;


public class Drive {
    private double MAXIMUM_SPEED;
    private File swerveJsonDirectory;
    public static SwerveDrive swerveDrive;

    public Drive() {
        MAXIMUM_SPEED = Constants.Drive.MAXIMUM_SPEED;

        swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");
        try {
            swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(MAXIMUM_SPEED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
    }
    
    public void periodic(double translationX, double translationY, double angularRotationX) {
        swerveDrive.drive(new Translation2d(translationX * MAXIMUM_SPEED,
            translationY * MAXIMUM_SPEED),
            angularRotationX * MAXIMUM_SPEED, 
                    true, false);

    }

}