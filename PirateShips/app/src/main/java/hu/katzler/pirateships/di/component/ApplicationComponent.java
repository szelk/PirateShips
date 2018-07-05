package hu.katzler.pirateships.di.component;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;

import javax.inject.Singleton;

import dagger.Component;
import hu.katzler.pirateships.App;
import hu.katzler.pirateships.di.ApplicationContext;
import hu.katzler.pirateships.di.module.ApplicationModule;
import hu.katzler.pirateships.util.PirateShipDownloader;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    PirateShipDownloader getPirateShipDownloader();

    //ImageLoader getImageLoader();

}
