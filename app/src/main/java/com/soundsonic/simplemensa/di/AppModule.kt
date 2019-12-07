package com.soundsonic.simplemensa.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.data.database.CanteenDao
import com.soundsonic.simplemensa.data.database.CanteenDatabase
import com.soundsonic.simplemensa.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object AppModule {

    @Provides
    @Singleton
    @Named("AppContext")
    fun appContext(app: Application): Context = app

    @Provides
    @Singleton
    fun provideSharedPreference(app: Application): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app)

    @Provides
    @Singleton
    fun openMensaApi(): OpenMensaApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(OpenMensaApi::class.java)

    @Provides
    fun provideItemAnimator(): RecyclerView.ItemAnimator = SlideInLeftAnimator().apply {
        addDuration = 300
        removeDuration = 300
        moveDuration = 300
        changeDuration = 300
    }

    @Provides
    fun provideSimpleDateFormat() = SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY)

    @Singleton
    @Provides
    fun provideCanteenDao(app: Application): CanteenDao = Room.databaseBuilder(
        app,
        CanteenDatabase::class.java,
        "canteen_database"
    ).build().canteenDao()
}
