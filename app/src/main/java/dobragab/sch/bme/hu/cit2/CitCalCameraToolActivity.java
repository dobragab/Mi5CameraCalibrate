package dobragab.sch.bme.hu.cit2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CitCalCameraToolActivity
        extends AppCompatActivity
        implements View.OnClickListener
{
    private Button mCalibrationBtn;
    private TextView mCalibrationResult;
    private TextView mCalibrationSummary;
    private ProgressBar mProgressBar;

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
        this.mCalibrationResult.setVisibility(View.GONE);

        this.mProgressBar = ((ProgressBar) findViewById(R.id.CalibrationProgress));
        this.mProgressBar.setVisibility(View.GONE);
    }

    public void onClick(View paramView)
    {
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... objects) {
                CameraCalibrator.Calibrate();
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mProgressBar.setVisibility(View.VISIBLE);
                mCalibrationResult.setVisibility(View.GONE);
                mCalibrationBtn.setEnabled(false);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mProgressBar.setVisibility(View.GONE);
                mCalibrationResult.setVisibility(View.VISIBLE);
                mCalibrationBtn.setEnabled(true);
            }
        };

        asyncTask.execute();
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_cit_cal_camera_tool);
        initResources();
    }
}