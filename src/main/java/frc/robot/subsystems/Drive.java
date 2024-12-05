import java.io.File;
import edu.wpi.first.wpilibj.Filesystem;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import edu.wpi.first.math.util.Units;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;


public class Drive {
    private double MAXIMUM_SPEED;
    private File swerveJsonDirectory;
    private SwerveDrive swerveDrive;

    public Drive() {
        MAXIMUM_SPEED = Constants.Drive.MAXIMUM_SPEED;
        swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");
        swerveDrive = new SwerveParser(directory).createSwerveDrive(MAXIMUM_SPEED);
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
    }
    
    public void periodic(double translationX; double translationY, double angularRotationX) {
        swerveDrive.drive(new Translation2d(translationX * swerveDrive.getMaximumVelocity(),
            translationY * swerveDrive.getMaximumVelocity()),
            angularRotationX. * swerveDrive.getMaximumAngularVelocity(), 
                    true, false);
    }
}