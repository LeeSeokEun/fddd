package com.example.leeseokeun.fddd;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//ffffffffffffffffffffff
public class MainActivity extends AppCompatActivity {
    private AudioReader audioReader;
    private int sampleRate = 8000;
    private int inputBlockSize = 256;
    private int sampleDecimate = 1;
    int temp = 0;
    String str =  String.valueOf(temp);
    //String str = String.format("%d",temp);
    TextView CCC;







    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CCC = (TextView) findViewById(R.id.test);
        CCC.setText(str);
        audioReader = new AudioReader();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);


    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        audioReader.startReader(sampleRate, inputBlockSize * sampleDecimate, new AudioReader.Listener()
        {
            @Override
            public final void onReadComplete(int dB)
            {
                receiveDecibel(dB);
            }

            @Override
            public void onReadError(int error)
            {
            }

        });
    }

    // 데시벨값이 음수에서 소리가 커지면 0에 가까워져서
    // 0에 가까운 숫자를 더해서 데시벨을 양수측으로 움직임
    // 기본 평소 대화소리를 40~60dB로 가정

    public void receiveDecibel(final int dB)
    {
        Log.e("###", dB+90 +" dB");
        temp = dB;
        //Log.e("###", temp+90 +" temp");

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        audioReader.stopReader();
    }


}

