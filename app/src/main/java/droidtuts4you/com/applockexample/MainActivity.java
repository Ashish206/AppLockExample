package droidtuts4you.com.applockexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private EditText mEnterPasswordField;
    private Button mSaveButton;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    private ToggleButton mToggleButton;
    private TextView mInstructText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEnterPasswordField  = (EditText) findViewById(R.id.enter_password);

        mToggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        mInstructText = (TextView) findViewById(R.id.instructText);

        mEnterPasswordField.setInputType(InputType.TYPE_CLASS_NUMBER);

        mSaveButton = (Button) findViewById(R.id.save_password);

        sharedpreferences = getSharedPreferences("MyPref",Context.MODE_PRIVATE);

        editor = sharedpreferences.edit();

        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mToggleButton.isChecked())
                {
                    editor.putBoolean("isEnable", true);
                    editor.commit();
                    mInstructText.setText("Disable Password Protection");
                }
                else
                {
                    SharedPreferences.Editor editor = getSharedPreferences("MyPref", MODE_PRIVATE).edit();
                    editor.putBoolean("isEnable", false);
                    editor.commit();
                    mInstructText.setText("Enable Password Protection");
                }
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String password = mEnterPasswordField.getText().toString();
                editor.putString("password", password);
                editor.commit();
                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                mEnterPasswordField.setText("");
            }
        });

    }

}
