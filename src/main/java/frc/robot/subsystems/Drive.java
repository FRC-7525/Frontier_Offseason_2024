package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;

import org.team7525.subsystem.Subsystem;

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


public class Drive extends Subsystem<AutoAlignStates> {
    private double MAXIMUM_SPEED;
    private File swerveJsonDirectory;
    public static SwerveDrive swerveDrive;
    private AutoAlign autoAligner;
    private XboxController controller;
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
        controller = new XboxController(0);

        addTrigger(AutoAlignStates.IDLE, AutoAlignStates.REEF, controller::getAButton);
        addTrigger(AutoAlignStates.IDLE, AutoAlignStates.CORAL, controller::getBButton);
        addTrigger(AutoAlignStates.REEF, AutoAlignStates.IDLE, controller::getAButton);
        addTrigger(AutoAlignStates.CORAL, AutoAlignStates.IDLE, controller::getBButton);
    }
    
    @Override
    public void runState() {
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