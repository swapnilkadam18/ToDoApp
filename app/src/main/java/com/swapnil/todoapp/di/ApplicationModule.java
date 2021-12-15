package com.swapnil.todoapp.di;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.swapnil.todoapp.model.network.NetworkServiceApi;
import com.swapnil.todoapp.model.persistence.TodoDao;
import com.swapnil.todoapp.model.persistence.TodoDatabase;
import com.swapnil.todoapp.model.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class ApplicationModule {

    @Provides
    @Singleton
    NetworkServiceApi provideNetworkServiceApiInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkServiceApi.class);
    }

    @Provides
    @Singleton
    TodoDatabase provideTodoDatabase(@ApplicationContext Context context){

        final Migration MIGRATION_UPDATE = new Migration(1, 2) {

            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `todonew` USING FTS4("
                        + "`title` TEXT, `description` TEXT, content=`todoItems`)");
                database.execSQL("INSERT INTO todonew (`newCol`, `name`, `description`) "
                        + "SELECT `id`, `title`, `description` FROM todoItems");

            }
        };

        return Room.databaseBuilder(
                context,
                TodoDatabase.class,
                "ToDoDBName")
                .addMigrations(MIGRATION_UPDATE)
                .build();
    }

    @Provides
    @Singleton
    TodoDao provideTodoDao(TodoDatabase database){
        return database.todoDao();
    }

}
