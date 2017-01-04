package com.quanlt.espressoexample.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.quanlt.espressoexample.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity {
    @BindView(R.id.list_contact)
    ListView contactListView;

    @Nullable
    @BindView(R.id.fragment_detail)
    FrameLayout detailFrameLayout;

    public static final String SAVED_CONTACT = "SAVED_CONTACT";

    private boolean isTwoLayout;
    private Contact selectedContact;
    private List<Contact> contacts = Arrays.asList(
            new Contact("LEIA ORGANA", "http://img.lum.dolimg.com/v1/images/Princess-Leia-Organa_d7761ff5.jpeg"),
            new Contact("KYLO REN", "http://img.lum.dolimg.com/v1/images/kylo-ren_fa163069.jpeg"),
            new Contact("HAN SOLO", "http://img.lum.dolimg.com/v1/images/Han-Solo_1d08eb2e.jpeg"),
            new Contact("BB-8", "http://img.lum.dolimg.com/v1/images/bb-8_14e2ad77.jpeg"),
            new Contact("R2-D2", "http://img.lum.dolimg.com/v1/images/R2-D2_41dacaa9.jpeg"),
            new Contact("REY", "http://img.lum.dolimg.com/v1/images/rey_bddd0f27.jpeg"),
            new Contact("POE DAMERON", "http://img.lum.dolimg.com/v1/images/poe-dameron_70f5aee2.jpeg"),
            new Contact("LUKE SKYWALKER", "http://img.lum.dolimg.com/v1/images/Luke-Skywalker_dd9c9f9b.jpeg"),
            new Contact("FN-2199", "http://img.lum.dolimg.com/v1/images/fn-2199_bfaed44a.jpeg")
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        isTwoLayout = detailFrameLayout != null;
        contactListView.setAdapter(new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts));
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedContact = contacts.get(position);
                if (isTwoLayout) {
                    detailFrameLayout.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_detail,
                                    DetailFragment.newInstance(selectedContact))
                            .commit();
                } else {
                    Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_CONTACT, selectedContact);
                    startActivity(intent);
                }
            }
        });
        if (savedInstanceState != null) {
            selectedContact = savedInstanceState.getParcelable(SAVED_CONTACT);
        }
        if (isTwoLayout)
            if (selectedContact != null) {
                detailFrameLayout.setVisibility(View.VISIBLE);
            } else {
                detailFrameLayout.setVisibility(View.GONE);
            }

    }
}
