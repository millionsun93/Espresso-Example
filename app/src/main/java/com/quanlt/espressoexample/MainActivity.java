package com.quanlt.espressoexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.quanlt.espressoexample.idling.LoginActivity;
import com.quanlt.espressoexample.list.ListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final int RC_LOGIN = 10;
    public static final String EXTRA_USERNAME = "";
    @BindView(R.id.text_username)
    TextView userNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_LOGIN) {
            String username = data.getStringExtra(EXTRA_USERNAME);
            if (!TextUtils.isEmpty(username))
                userNameTextView.setText(username);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent, RC_LOGIN);
                break;
            case R.id.action_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.action_list:
                startActivity(new Intent(this, ListActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
