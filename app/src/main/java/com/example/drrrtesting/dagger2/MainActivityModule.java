package com.example.drrrtesting.dagger2;


import com.example.drrrtesting.mainAct.MainActPresenterImpl;
import com.example.drrrtesting.mainAct.MainActView;
import com.example.drrrtesting.mainAct.MainActivity;
import com.example.drrrtesting.room.dao.DaoUsers;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {

    @Provides
    static MainActPresenterImpl provideMAPresenter(DaoUsers daoUsers,MainActView mainActView){
        return new MainActPresenterImpl(daoUsers, mainActView);
    }

    @Binds
    abstract MainActView bindsMainActView(MainActivity mainActivity);
}
