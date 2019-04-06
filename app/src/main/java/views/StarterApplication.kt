package views

import android.app.Application
import com.parse.Parse
import com.parse.ParseACL


class StarterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Parse.enableLocalDatastore(this)
        Parse.initialize(Parse.Configuration.Builder(this)
                .applicationId(BACK4APP_APPLICATION_ID)
                .clientKey(BACK4APP_CLIENT_KEY)
                .server(BACK4APP_SERVER)
                .build())

        val defaultACL = ParseACL()
        defaultACL.publicReadAccess = true
        defaultACL.publicWriteAccess = true
        ParseACL.setDefaultACL(defaultACL, true)
    }

    companion object {
        val BACK4APP_APPLICATION_ID = "Bhkk6VdvDtUaQeKNPDooe2rtqCYcSjNKygSutSR3"
        val BACK4APP_CLIENT_KEY = "DKwEHmROf40DtYmxxwl8NVF48kBAZXCgIx8PQECe"
        val BACK4APP_SERVER = "https://parseapi.back4app.com/"
    }
}



