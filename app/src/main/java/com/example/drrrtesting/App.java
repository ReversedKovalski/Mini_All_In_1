package com.example.drrrtesting;


import com.example.drrrtesting.dagger2.AppComponent;
import com.example.drrrtesting.dagger2.DaggerAppComponent;


import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {

    @Override
    public AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent;
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();
        appComponent.inject(this);
        return appComponent;
    }
}
