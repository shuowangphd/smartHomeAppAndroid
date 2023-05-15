package com.example.smarthomegcapp;

        import android.app.Activity;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.VideoView;

        import androidx.appcompat.app.AppCompatActivity;

public class Screen2 extends AppCompatActivity {
    int id=0;
    int[] vlist = {R.raw.h0,R.raw.h1,R.raw.h2,R.raw.h3,R.raw.h4,R.raw.h5,R.raw.h6,R.raw.h7
            ,R.raw.h8,R.raw.h9,R.raw.hlighton,R.raw.hlightoff,R.raw.hfanon,R.raw.hfanoff,
            R.raw.hincreasefanspeed,R.raw.hdecreasefanspeed,R.raw.hsetthermo};
    String[] slist = {"0","1","2","3","4","5","6","7","8","9","Turn on lights","Turn off lights",
            "Turn on fan","Turn off fan","Increase fan speed",
            "Decrease fan speed","Set Thermostat to specified temperature"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s2);
        TextView tv = (TextView) findViewById(R.id.expertTitle);
        Bundle bd = getIntent().getExtras();
        String gid = bd.getString("gid");
        tv.setText(bd.getString(gid));
        for(;id<16;id++) if(slist[id].equals(gid)) break;
        VideoView vv = (VideoView) findViewById(R.id.videoView);
        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + vlist[id]));
        vv.start();
    }
    public void repOnClick(View v){
        VideoView vv = (VideoView) findViewById(R.id.videoView);
        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + vlist[id]));
        vv.start();
    }
    public void pracOnClick(View v){
        Intent intent = new Intent(v.getContext(), Screen3.class);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }
}