package frc.robot;

import edu.wpi.first.math.util.Units;


public final class Constants {

    private Constants() {

    }

    public static final class Shooter {
        public static final double SHOOTING_RPS = 80;
        public static final int LEFT_MOTOR_ID = 15;
        public static final int RIGHT_MOTOR_ID = 14;
        public static final double SLOW_RPS = 20;
    }
     

    public static final class Intake {
        public static final int PIVOT_MOTOR_ID = 10;
        public static final int ROTATIONAL_MOTOR_ID = 20; 
        public static final double IDLE_SPEED = 0; 
        public static final double PASSING_SPEED = 1; 
        public static final double INTAKING_SPEED = 0.29;
        public static final double IN = 0;
        public static final double OUT = -37; 
        public static final double SLOW = -0.25;
    }

    public static final class AmpBar { // TODO FIX ALL PLEASE!!!!!
        public static final int ROTATIONAL_MOTOR_ID = 38; 
        public static final int LPIVOT_MOTOR_ID = 30;
        public static final int RPIVOT_MOTOR_ID = 35; 
        public static final double IN = Units.degreesToRotations(0); 
        public static final double OUT = 180; 
        public static final double IDLE_SPEED = 0;
        public static final double FEEDING_SPEED = 0.1;
        public static final double SHOOTING_SPEED = 0.5;
        
    
    }

    public static final class Drive {
        public static final double MAXIMUM_SPEED = Units.feetToMeters(4.5);
    }

}
