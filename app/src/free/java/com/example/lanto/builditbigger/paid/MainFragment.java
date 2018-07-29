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


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

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
                loadInterstitialAdd();

        }});

        return rootView;
    }

    @SuppressLint("StaticFieldLeak")
    private void showJoke(){
        new GetJokeAsync() {
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

    //initialize interstitial add
    public void loadInterstitialAdd(){
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
                showJoke();
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getActivity(), R.string.load_joke_toast, Toast.LENGTH_LONG).show();
                showJoke();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(spinner.getVisibility() == View.VISIBLE){
            spinner.setVisibility(View.INVISIBLE);
        }
    }
}
