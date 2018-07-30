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

import com.example.lanto.builditbigger.GetJokeAsync;
import com.example.lanto.builditbigger.R;
import com.example.showjoke.ShowJokeActivity;

import static com.example.showjoke.ShowJokeActivity.KEY_JOKE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private ProgressBar spinner;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().setTitle(getString(R.string.paid_version_title));


        spinner = rootView.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.INVISIBLE);

        Button button = rootView.findViewById(R.id.main_button_id);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                new GetJokeAsync(){
                    @Override
                    protected void onPostExecute(String result) {
                        Log.e("In async", "onPost Execute: " + result);
                        Intent intent = new Intent(getActivity(), ShowJokeActivity.class);
                        intent.putExtra(KEY_JOKE, result);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getActivity().startActivity(intent);
                    }
                }.execute();

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(spinner.getVisibility() == View.VISIBLE){
            spinner.setVisibility(View.INVISIBLE);
        }
    }

}
