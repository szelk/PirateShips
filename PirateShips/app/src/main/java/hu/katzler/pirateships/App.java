package hu.katzler.pirateships;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import hu.katzler.pirateships.di.component.ApplicationComponent;
import hu.katzler.pirateships.di.component.DaggerApplicationComponent;
import hu.katzler.pirateships.di.module.ApplicationModule;

public class App extends Application {
    private ApplicationComponent applicationComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = createApplicationComponent();
        initImageLoader();
    }

    private ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
