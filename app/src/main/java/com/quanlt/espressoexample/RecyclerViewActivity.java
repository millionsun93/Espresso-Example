package com.quanlt.espressoexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rl_numbers)
    RecyclerView mCharacterRecyclerView;

    @BindView(R.id.text_selected_item)
    TextView mSelectedText;

    private static List<Integer> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        items = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            items.add(i);
        }
        CharacterAdapter adapter = new CharacterAdapter(new CharacterAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                mSelectedText.setText("#" + position + " is selected");
            }
        });

        mCharacterRecyclerView.setAdapter(adapter);
        mCharacterRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public static class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
        private final OnItemClick mListener;

        CharacterAdapter(OnItemClick mListener) {
            this.mListener = mListener;
        }

        @Override
        public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new CharacterViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CharacterViewHolder holder, final int position) {
            holder.mTitleTextView.setText("#" + items.get(position).toString());
            holder.mTitleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public static class CharacterViewHolder extends RecyclerView.ViewHolder {
            TextView mTitleTextView;

            public CharacterViewHolder(View itemView) {
                super(itemView);
                mTitleTextView = (TextView) itemView;
            }
        }

        interface OnItemClick {
            void onItemClick(int position);
        }
    }
}
