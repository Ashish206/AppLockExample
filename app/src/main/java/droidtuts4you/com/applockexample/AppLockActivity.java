package droidtuts4you.com.applockexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AppLockActivity extends AppCompatActivity {
    private Button mOkBtn;
    private SharedPreferences sharedpreferences;
    private String mPassword;
    private String mInputPass;
    private Boolean isLockEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_lock);
        mOkBtn = (Button) findViewById(R.id.btn_ok); 

        sharedpreferences = getSharedPreferences("MyPref",Context.MODE_PRIVATE);

        mPassword = sharedpreferences.getString("password", null);

        isLockEnable = sharedpreferences.getBoolean("isEnable", Boolean.parseBoolean(null));

        if (isLockEnable.equals(false))
        {
            Intent intent = new Intent(AppLockActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputPass = KeyboardView.mPasswordField.getText().toString();
                if (mInputPass.equals(mPassword))
                {
                    Intent intent = new Intent(AppLockActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(AppLockActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
