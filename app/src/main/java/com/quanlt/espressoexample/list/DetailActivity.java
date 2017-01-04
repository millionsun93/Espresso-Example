package com.quanlt.espressoexample.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.quanlt.espressoexample.R;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_CONTACT = "Contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Contact contact = getIntent().getParcelableExtra(EXTRA_CONTACT);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_detail, DetailFragment.newInstance(contact))
                .commit();
    }
}
