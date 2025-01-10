package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import edu.wpi.first.math.geometry.Translation2d;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;
import frc.robot.Constants;
import frc.robot.subsystems.AutoAlign.AutoAlign;
import frc.robot.subsystems.AutoAlign.AutoAlignStates;


public class Drive {
    private double MAXIMUM_SPEED;
    private File swerveJsonDirectory;
    public static SwerveDrive swerveDrive;
    private AutoAlign autoAligner;
    private XboxController controller;
    public Drive() {
        MAXIMUM_SPEED = Constants.Drive.MAXIMUM_SPEED;

        swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");
        try {
            swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(MAXIMUM_SPEED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;

        autoAligner = new AutoAlign(swerveDrive);
        controller = new XboxController(0);
    }
    
    public void periodic() {
        if (autoAligner.getState() == AutoAlignStates.IDLE) {
            swerveDrive.drive(new Translation2d(controller.getLeftX() * MAXIMUM_SPEED,
                controller.getLeftY() * MAXIMUM_SPEED),
                controller.getRightX() * MAXIMUM_SPEED, 
                    true, false);
        } else {
            autoAligner.runState();
        }
        

    }

}