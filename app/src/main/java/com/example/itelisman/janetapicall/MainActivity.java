package com.example.itelisman.janetapicall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.itelisman.janetapicall.model.Song;
import com.example.itelisman.janetapicall.network.JanetClient;
import com.example.itelisman.janetapicall.network.SampleHttpAction;

import io.techery.janet.ActionPipe;
import io.techery.janet.helper.ActionStateSubscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView1);
        makeApiCall();
    }

    void makeApiCall(){
        // create pipe for action class with janet
        ActionPipe<SampleHttpAction> actionPipe = JanetClient.getJanet().createPipe(SampleHttpAction.class);

        // register request result observer
        actionPipe.createObservable(new SampleHttpAction("rock"))
                .subscribeOn(Schedulers.io())
                .subscribe(new ActionStateSubscriber<SampleHttpAction>()
                .onStart(action -> System.out.println("Request is being sent " + action))
                .onProgress((action, progress) -> System.out.println("Request in progress: " + progress))
                .onSuccess(action -> setTextViews(action.songsResponse.getSongs().get(0)))
                .onFail((action, throwable) -> System.err.println("Request failed " + throwable))
        );

    }

    public void setTextViews(Song song){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Stuff that updates the UI
                textView.setText(song.getArtistName());
            }
        });

    }
}
