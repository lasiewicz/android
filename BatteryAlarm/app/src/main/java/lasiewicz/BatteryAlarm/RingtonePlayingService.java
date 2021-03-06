package lasiewicz.BatteryAlarm;


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Random;

public class RingtonePlayingService extends Service {

    private boolean isRunning;
    private Context context;
    MediaPlayer mMediaPlayer;
    private int startId;

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.e("MyActivity", "In the service");
        return null;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {


        final NotificationManager mNM = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, 0);

        Notification mNotify  = new Notification.Builder(this)
                .setContentTitle("alarm is playing" + "!")
                .setContentText("Click me!")
                .setSmallIcon(R.drawable.ic_action_call)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();

        String state = intent.getExtras().getString("extra");
        Boolean isCharging=false;
        Log.e("what is going on here  ", state);

        assert state != null;
        switch (state) {
            case "no":
                startId = 0;
                break;
            case "yes":
                startId = 1;
                break;
            default:
                startId = 0;
                break;
        }

        // get battery's thing
        String sounds_id = intent.getExtras().getString("quote id");
        Log.e("Service: sound id is " , sounds_id);
        isCharging=false;
        try {
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, ifilter);
            if (batteryStatus != null)
            {
                int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL;
            }

        }
        catch (Exception expt)
        {
            String texterror= expt.toString() + " crashed checking plugged in";
            Log.e("crashed  ", texterror);
        }
        if(!this.isRunning && startId == 1 && !isCharging) {
            Log.e("if there was not sound ", " and you want start");

            assert sounds_id != null;
            if (sounds_id.equals("0")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.buzzer);
                mMediaPlayer = MediaPlayer.create(this, R.raw.buzzer);
                mMediaPlayer = MediaPlayer.create(this, R.raw.buzzer);
                mMediaPlayer = MediaPlayer.create(this, R.raw.buzzer);
                mMediaPlayer = MediaPlayer.create(this, R.raw.buzzer);
            }
            else if (sounds_id.equals("1")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.rainthunder);
                mMediaPlayer = MediaPlayer.create(this, R.raw.rainthunder);
            }
            else if (sounds_id.equals("2")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.frogs);
                mMediaPlayer = MediaPlayer.create(this, R.raw.frogs);
            }

            else if (sounds_id.equals("6")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.alertbomb);
                mMediaPlayer = MediaPlayer.create(this, R.raw.alertbomb);
            }
            else if (sounds_id.equals("4")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.whale);
                mMediaPlayer = MediaPlayer.create(this, R.raw.whale);
            }
            else if (sounds_id.equals("5")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.computerlove);
                mMediaPlayer = MediaPlayer.create(this, R.raw.computerlove);
            }
            else if (sounds_id.equals("3")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.plugyourphonein);
                mMediaPlayer = MediaPlayer.create(this, R.raw.plugyourphonein);
                mMediaPlayer = MediaPlayer.create(this, R.raw.plugyourphonein);
                mMediaPlayer = MediaPlayer.create(this, R.raw.plugyourphonein);
                mMediaPlayer = MediaPlayer.create(this, R.raw.plugyourphonein);
            }
            else if (sounds_id.equals("6")) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.frogs);
                mMediaPlayer = MediaPlayer.create(this, R.raw.frogs);
            }
            else {
                mMediaPlayer = MediaPlayer.create(this, R.raw.plugyourphonein);
                mMediaPlayer = MediaPlayer.create(this, R.raw.plugyourphonein);
                mMediaPlayer = MediaPlayer.create(this, R.raw.plugyourphonein);
                mMediaPlayer = MediaPlayer.create(this, R.raw.plugyourphonein);
                mMediaPlayer = MediaPlayer.create(this, R.raw.plugyourphonein);

            }


            mMediaPlayer.start();

            mNM.notify(0, mNotify);

            this.isRunning = true;
            this.startId = 0;

        }
        else if (!this.isRunning && startId == 0){
            Log.e("if there was not sound ", " and you want end");

            this.isRunning = false;
            this.startId = 0;

        }

        else if (this.isRunning && startId == 1){
            Log.e("if there is sound ", " and you want start");

            this.isRunning = true;
            this.startId = 0;

        }
        else {
            Log.e("if there is sound ", " and you want end");

            mMediaPlayer.stop();
            mMediaPlayer.reset();

            this.isRunning = false;
            this.startId = 0;
        }


        Log.e("MyActivity", "In the service");

        return START_NOT_STICKY;
    }



    @Override
    public void onDestroy() {
        Log.e("JSLog", "on destroy called");
        super.onDestroy();

        this.isRunning = false;
    }


}