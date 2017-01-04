package com.quanlt.espressoexample.idling;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.quanlt.espressoexample.MainActivity;
import com.quanlt.espressoexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.edit_username)
    EditText usernameEditText;
    @BindView(R.id.text_result)
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button_submit)
    public void submit() {
        final LoadingProgress progress = new LoadingProgress(this, getString(R.string.login));
        progress.show(getSupportFragmentManager(), progress.TAG);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (usernameEditText.getText().toString().equals("rightuser")) {
                    resultTextView.setText("Success");
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.EXTRA_USERNAME, usernameEditText.getText().toString());
                    setResult(RESULT_OK, intent);
                } else {
                    resultTextView.setText("Failed");
                }
                progress.dismiss();
            }
        }, 1000);
    }
}
