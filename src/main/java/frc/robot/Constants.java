package frc.robot;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.XboxController;

public class Constants {
    public static final XboxController DRIVER_CONTROLLER = new XboxController(0);

    public static final class Drive {
        public static final class Real {
            
        }
        public static final class Sim {
            public static final double MAX_SPEED = Units.feetToMeters(16);
        }
    }

    public static final class AutoAligner {
        public static final List<Pose2d> NEAREST_FEEDER = Arrays.asList(
            new Pose2d(new Translation2d(1.24,7.1), Rotation2d.fromDegrees(125)),
            new Pose2d(new Translation2d(1.24,1), Rotation2d.fromDegrees(0))
        );
        public static final List<Pose2d> NEAREST_REEF = Arrays.asList(
            new Pose2d(new Translation2d(5.15,5.15), Rotation2d.fromDegrees(-120)),
            new Pose2d(new Translation2d(5.9,4.1), Rotation2d.fromDegrees(180)),
            new Pose2d(new Translation2d(5.3,2.8), Rotation2d.fromDegrees(120)),
            new Pose2d(new Translation2d(3.8,2.7), Rotation2d.fromDegrees(60)),
            new Pose2d(new Translation2d(3, 4), Rotation2d.fromDegrees(0)),
            new Pose2d(new Translation2d(3.85, 5.2), Rotation2d.fromDegrees(-60))
        );
    }
}
