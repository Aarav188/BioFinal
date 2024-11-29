package org.firstinspires.ftc.teamcode.configs;

public class Field {
    public static final float MM_PER_INCH = 25.4f;
    public static final float TARGET_HEIGHT = (6) * MM_PER_INCH;
    public static final double M_PER_INCH = MM_PER_INCH / 1000;
    //the width of each tile
    public static final double TILE_WIDTH = 24 * MM_PER_INCH;
    // the width of the FTC field (from the center point to the outer panels)
    // public static final double FIELD_WIDTH = 6 * TILE_WIDTH;
    public static final double TAPE_WIDTH = 2 * MM_PER_INCH;
    private static final int FIELD_WIDTH_INCHES = (12 * 12) - 3; // Fields are 11' 9" inside the walls
    public static final int FIELD_WIDTH = (int) (FIELD_WIDTH_INCHES * MM_PER_INCH);
    public static final int HALF_FIELD_WIDTH = (int) ((FIELD_WIDTH_INCHES * MM_PER_INCH) / 2.0f);


    public enum Alliance {
        BLUE, RED, WHITE, NEUTRAL;

        public static Alliance opposite(Alliance color) {
            switch (color) {
                case RED:
                    color = Alliance.BLUE;
                    break;
                case BLUE:
                    color = Alliance.RED;
                    break;
                case WHITE:
                    color = Alliance.WHITE;
            }
            return color;
        }
    }
}