package com.example.android.candypod.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import timber.log.Timber;

import static com.example.android.candypod.utilities.Constants.DATABASE_NAME;

@Database(entities = {PodcastEntry.class}, version = 1, exportSchema = false)
public abstract class CandyPodDatabase extends RoomDatabase {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static CandyPodDatabase sInstance;

    public static CandyPodDatabase getInstance(Context context) {
        Timber.d("Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        CandyPodDatabase.class, DATABASE_NAME).build();
                Timber.d("Made new database");
            }
        }
        return sInstance;
    }

    // The associated DAOs for the database
    public abstract PodcastDao podcastDao();
}