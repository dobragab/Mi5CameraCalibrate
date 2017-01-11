package dobragab.sch.bme.hu.cit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CitCalCameraToolActivity
        extends AppCompatActivity
        implements View.OnClickListener
{
    private Button mCalibrationBtn;
    private TextView mCalibrationResult;
    private TextView mCalibrationSummary;

    protected void initResources()
    {
        this.mCalibrationSummary = ((TextView)findViewById(R.id.TextView1));
        this.mCalibrationBtn = ((Button)findViewById(R.id.Button));
        this.mCalibrationBtn.setOnClickListener(this);
        String str = SystemPropertiesProxy.get(this, "ro.boot.hwversion", "");
        if ((!str.substring(0, 1).equals("1")) && (!str.substring(0, 1).equals("2"))) {
            this.mCalibrationBtn.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Device not supported.", Toast.LENGTH_SHORT).show();
        }
        this.mCalibrationResult = ((TextView)findViewById(R.id.TextView2));
        this.mCalibrationResult.setVisibility(View.INVISIBLE);
    }

    public void onClick(View paramView)
    {
        CameraCalibrator.Calibrate();
        this.mCalibrationResult.setVisibility(View.VISIBLE);
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_cit_cal_camera_tool);
        initResources();
    }
}