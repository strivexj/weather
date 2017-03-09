package example.com.map.example.com.furtherstudy;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import example.com.map.R;


public class MusicService extends Service {
    private MediaPlayer mediaPlayer=new MediaPlayer();
    public MusicService() {
       // Log.d("MyService","onMyService executed");
        // if(!mediaPlayer.isPlaying()) {
    /*  new Thread(new Runnable() {
          @Override
          public void run() {
              Log.d("MyService","Thread id is"+Thread.currentThread().getId());
              mediaPlayer= MediaPlayer.create(getApplicationContext(), R.raw.music);
              mediaPlayer.start();
          }
      }).start();*///}

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.d("MyService","onStartCommand executed");
        if(!mediaPlayer.isPlaying()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                  //  Log.d("MyService","Thread id is"+Thread.currentThread().getId());
                    mediaPlayer= MediaPlayer.create(getApplicationContext(), R.raw.music);
                  //  Log.d("Music a",""+mediaPlayer.getDuration());
                    mediaPlayer.start();
                }
            }).start();}
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
      //  Log.d("MyService","onDestroy executed");
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
     //   Log.d("MyService","onCreate executed");
    }
}
