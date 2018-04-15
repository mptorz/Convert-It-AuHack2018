package auhack.epinoodle.convertit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    EditText convFrom;
    EditText convTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }

    public void cont(View view) {
        convFrom = findViewById(R.id.conv_from);
        convTo = findViewById(R.id.conv_to);
        if (convFrom.getText() != null
                && convFrom.getText().toString().matches("\\b[A-Za-z]{3}\\b")
                && convTo.getText() != null
                && convTo.getText().toString().matches("\\b[A-Za-z]{3}\\b")){
            Intent intent = new Intent(this, OcrCaptureActivity.class);
            intent.putExtra("currFromText", convFrom.getText().toString());
            intent.putExtra("currToText", convTo.getText().toString());
            startActivity(intent);
        }
    }
}
