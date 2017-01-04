package com.quanlt.espressoexample.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quanlt.espressoexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by quanlt on 04/01/2017.
 */

public class DetailFragment extends Fragment {

    @BindView(R.id.text_detail_name)
    TextView nameTextView;
    @BindView(R.id.image_detail_avatar)
    ImageView avatarImageView;

    public static DetailFragment newInstance(Contact contact) {

        Bundle args = new Bundle();
        args.putParcelable(DetailActivity.EXTRA_CONTACT, contact);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        Contact contact = getArguments().getParcelable(DetailActivity.EXTRA_CONTACT);
        if (contact == null) {
            throw new IllegalStateException("Contact should not be null");
        }
        nameTextView.setText(contact.name);
        Glide.with(this).load(contact.avatar)
                .placeholder(R.mipmap.ic_launcher)
                .into(avatarImageView);
        return view;
    }
}
