package lasiewicz.BatteryAlarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lasiewiw on 6/17/2016.
 */
public class PlugInControlReceiver extends BroadcastReceiver {
    public PlugInControlReceiver() {
    }

    public void onReceive(Context context , Intent intent) {

        String action = intent.getAction();

        if(action.equals(Intent.ACTION_POWER_CONNECTED)) {

           // isPluggedin=true;
           // pluggedin=true;
        }
        else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {

           // pluggedin=false;
        }
    }
}

