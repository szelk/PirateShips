package hu.katzler.pirateships;

import android.app.Application;
import android.content.Context;

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
}
