package dobragab.sch.bme.hu.cit2;

import android.hardware.Camera;

public class CameraCalibrator {

    public static void Calibrate() {

        Camera camera = Camera.open();
        Camera.Parameters localParameters = camera.getParameters();
        localParameters.set("ois-re-calibration", "true");
        camera.setParameters(localParameters);
        camera.release();

    }
}
