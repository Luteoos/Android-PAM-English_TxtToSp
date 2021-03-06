package io.github.luteoos.learnenglish

import android.app.Application
import android.os.StrictMode
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

class EnglishApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.e(this.toString())
        if(BuildConfig.DEBUG)
            initDebugStuff()
        Realm.init(this)
        val realmConfiguration = RealmConfiguration
            .Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
        //SessionManager.init(applicationContext)
    }

    private fun initDebugStuff() {
        Timber.plant(Timber.DebugTree())

        Timber.e("initDebugStuff")
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build())
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build())
    }
}