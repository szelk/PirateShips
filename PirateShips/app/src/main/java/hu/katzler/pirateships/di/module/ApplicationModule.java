package hu.katzler.pirateships.di.module;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;

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
//    @Singleton
//    ImageLoader getImageLoader() {
//        return ImageLoader.getInstance();
//    }

}

