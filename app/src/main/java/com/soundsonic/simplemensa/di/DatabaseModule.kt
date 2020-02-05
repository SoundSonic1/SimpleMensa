package com.soundsonic.simplemensa.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.soundsonic.simplemensa.data.database.CanteenDao
import com.soundsonic.simplemensa.data.database.CanteenDatabase
import com.soundsonic.simplemensa.data.database.UserProfileDao
import com.soundsonic.simplemensa.data.database.UserProfileDatabase
import com.soundsonic.simplemensa.data.model.UserProfile
import com.soundsonic.simplemensa.util.Constants.USER_ID
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Module
object DatabaseModule {

    private lateinit var userDatabase: UserProfileDatabase

    @Singleton
    @Provides
    fun provideCanteenDao(app: Application): CanteenDao = Room.databaseBuilder(
        app,
        CanteenDatabase::class.java,
        "canteen_database"
    ).build().canteenDao()

    @Singleton
    @Provides
    fun provideUserProfileDb(app: Application): UserProfileDatabase {
        userDatabase = Room.databaseBuilder(
            app,
            UserProfileDatabase::class.java,
            "user_profile_database"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.Default).launch {
                    Log.d("UserDatabase", "creating user")
                    userDatabase.userProfileDao().insert(
                        UserProfile(USER_ID)
                    )
                }
            }
        }).build()

        CoroutineScope(Dispatchers.IO).launch {
            userDatabase.userProfileDao().findUserById(USER_ID)
        }

        return userDatabase
    }

    @Singleton
    @Provides
    fun provideUserProfileDao(userDB: UserProfileDatabase): UserProfileDao = userDB.userProfileDao()
}
