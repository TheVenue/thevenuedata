package views;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.parse.Parse;
import com.parse.ParseACL;



public class StarterApplication extends Application {

    //  Back4App information.
    public static final String BACK4APP_APPLICATION_ID = "Bhkk6VdvDtUaQeKNPDooe2rtqCYcSjNKygSutSR3";
    public static final String BACK4APP_CLIENT_KEY = "DKwEHmROf40DtYmxxwl8NVF48kBAZXCgIx8PQECe";
    public static final String BACK4APP_SERVER = "https://parseapi.back4app.com/";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Connect to Your Back4app Account
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(BACK4APP_APPLICATION_ID)
                .clientKey(BACK4APP_CLIENT_KEY)
                .server(BACK4APP_SERVER)
                .build());

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}



