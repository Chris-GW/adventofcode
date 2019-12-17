package de.adventofcode.chrisgw.day17;

public enum CameraPixel {

    SCAFFOLD('#'), //
    SCAFFOLD_INTERSECTION('O'), //
    EMPTY('.'), //
    ROBOT_UP('^'), //
    ROBOT_RIGHT('>'), //
    ROBOT_DOWN('v'), //
    ROBOT_LEFT('<');

    private char cameraPixelSign;


    CameraPixel(char cameraPixelSign) {
        this.cameraPixelSign = cameraPixelSign;
    }

    public static CameraPixel valueOf(char cameraPixelSign) {
        for (CameraPixel cameraPixel : CameraPixel.values()) {
            if (cameraPixel.getCameraPixelSign() == cameraPixelSign) {
                return cameraPixel;
            }
        }
        throw new IllegalArgumentException("Unknown cameraPixel: " + cameraPixelSign);
    }


    public boolean isEmpty() {
        return this.equals(EMPTY);
    }

    public boolean isScaffoldPixel() {
        return !this.equals(EMPTY);
    }

    public boolean isRobotPixel() {
        return this.equals(ROBOT_UP) || this.equals(ROBOT_RIGHT) || this.equals(ROBOT_DOWN) || this.equals(ROBOT_LEFT);
    }


    public char getCameraPixelSign() {
        return cameraPixelSign;
    }

}
