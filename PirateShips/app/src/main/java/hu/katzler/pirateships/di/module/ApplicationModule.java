package hu.katzler.pirateships.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.katzler.pirateships.di.ApplicationContext;
import hu.katzler.pirateships.util.HttpHandler;
import hu.katzler.pirateships.util.PirateShipDownloader;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    HttpHandler provideHttpHandler() {
        return new HttpHandler();
    }

    @Provides
    @Singleton
    PirateShipDownloader providePirateShipDownloader(HttpHandler httpHandler) {
        return new PirateShipDownloader(httpHandler);
    }

//    @Provides
//    @DatabaseInfo
//    String provideDatabaseName() {
//        return "demo-dagger.db";
//    }
//
//    @Provides
//    @DatabaseInfo
//    Integer provideDatabaseVersion() {
//        return 2;
//    }
//
//    @Provides
//    SharedPreferences provideSharedPrefs() {
//        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
//    }
}

