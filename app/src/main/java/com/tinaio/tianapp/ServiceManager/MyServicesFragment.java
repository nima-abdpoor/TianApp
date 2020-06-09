package com.tinaio.tianapp.ServiceManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.tinaio.tianapp.R;
import com.tinaio.tianapp.Services.Attendance;

import java.util.ArrayList;
import java.util.List;

public class MyServicesFragment extends Fragment implements View.OnClickListener {
    List<String> text;
    List<String> id;
    View view;
    List<TextView> textViews;
    List<CardView> cardViews;
    Context context;
    int size;

    public MyServicesFragment(Context context) {
        this.context = context;
    }

    public MyServicesFragment() {
    }

    public static MyServicesFragment NewInstance(ArrayList<String> text, ArrayList<String> id) {
        MyServicesFragment fragment = new MyServicesFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("text", text);
        bundle.putStringArrayList("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        text = getArguments().getStringArrayList("text");
        id = getArguments().getStringArrayList("id");
        size = text.size();
        view = inflater.inflate(R.layout.services_fragment, container, false);
        InitViewItems(size);
        textViews = new ArrayList<>();
        cardViews = new ArrayList<>();
        int[] text_id = new int[]{R.id.text_1, R.id.text_2, R.id.text_3, R.id.text_4, R.id.text_5, R.id.text_6, R.id.text_7, R.id.text_8, R.id.text_9};
        int[] card_id = new int[]{R.id.card_1, R.id.card_2, R.id.card_3, R.id.card_4, R.id.card_5, R.id.card_6, R.id.card_7, R.id.card_8, R.id.card_9};
        for (int i = 0; i < size; ++i) {
            textViews.add((TextView) view.findViewById(text_id[i]));
            cardViews.add((CardView) view.findViewById(card_id[i]));
            textViews.get(i).setVisibility(View.VISIBLE);
            cardViews.get(i).setVisibility(View.VISIBLE);
            cardViews.get(i).setOnClickListener(this);
        }
        SetViewTexts(size, text);
        return view;
    }

    private void SetViewTexts(int size, List<String> text) {
        for (int i = 0; i < size; ++i) {
            textViews.get(i).setText(text.get(i));
        }
    }

    private void InitViewItems(int size) {
    }

    public void Clickable(Context context) {
        this.context = context;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_1:
                startActivity(new Intent(context, Attendance.class));
                break;
        }
    }
}
