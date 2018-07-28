package com.example.lanto.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.javajokes.Jokes;
import com.example.showjoke.ShowJokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainFragment extends Fragment {

    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //add ad
        AdView mAdView = (AdView) rootView.findViewById(R.id.main_ad_view);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        Button button = rootView.findViewById(R.id.main_button_id);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            public void onClick(View v) {
                new GetJokeAsync(getActivity()){
                    @Override
                    protected void onPostExecute(String result) {
                            Log.e("In async", "onPost Execute: " + result);
                            Intent intent = new Intent(getActivity(), ShowJokeActivity.class);
                            intent.putExtra("joke", result);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(intent);
                    }
                }.execute();

            }
        });

        return rootView;
    }
}
