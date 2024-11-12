package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {

    private Constants() {

    }

    public static final class Shooter {
        public static final double speed = 80;
    }
     

    public static final class Intake {
        public static final double speed = -1;
        public static final double in = Units.degreesToRotations(0); 
        public static final double out = Units.degreesToRotations(180); 
    }

    public static final class AmpBar {
        public static final int ROTATIONAL_MOTOR_ID = 0; //TODO fix all this
        public static final int LPIVOT_MOTOR_ID = 0; //TODO fix this
        public static final int RPIVOT_MOTOR_ID = 0; //TODO fix this
    }

}
