package com.example.drrrtesting.dagger2;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

import com.example.drrrtesting.App;
import com.example.drrrtesting.room.RoomDB;
import com.example.drrrtesting.room.dao.DaoUsers;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application){
        return application;
    }

    @Provides
    @Singleton
    RoomDB provideRoomDB(Context mContext){
        return Room.databaseBuilder(mContext, RoomDB.class, "database").fallbackToDestructiveMigration().build();
    }

    @Provides
    @Singleton
    DaoUsers provideDaoUsers(RoomDB roomDB){
        return roomDB.daoUsers();
    }
}
