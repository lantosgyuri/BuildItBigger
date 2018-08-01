package com.example.lanto.builditbigger.paid;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lanto.builditbigger.GetJokeAsync;
import com.example.lanto.builditbigger.R;
import com.example.showjoke.ShowJokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.InterstitialAd;

import static com.example.showjoke.ShowJokeActivity.KEY_JOKE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements GetJokeAsync.getJOkeAsyncCallback {

    private ProgressBar spinner;
    private InterstitialAd mInterstitialAd;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().setTitle(getString(R.string.free_version_title));

        spinner = rootView.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.INVISIBLE);

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
                spinner.setVisibility(View.VISIBLE);
                getJoke();

        }});

        return rootView;
    }

    @SuppressLint("StaticFieldLeak")
    private void getJoke(){
        GetJokeAsync getJokeAsync = new GetJokeAsync();
        getJokeAsync.setCallback(this);
        getJokeAsync.execute();
    }



    //initialize interstitial add
    public void loadInterstitialAdd(final String result){
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }

            @Override
            public void onAdClosed() {
                showJoke(result);
            }
        });
    }

    //if joke can not load, just show a toast
    @Override
    public void jokeDone(String result, boolean failedToLoad) {
        //joke loaded spinner not needed anyomre
        spinner.setVisibility(View.INVISIBLE);
        if(failedToLoad) {
            Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
        } else {
            loadInterstitialAdd(result);
        }
    }

    private void showJoke(String result){
        Intent intent = new Intent(getActivity(), ShowJokeActivity.class);
        intent.putExtra(KEY_JOKE, result);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
    }
}
