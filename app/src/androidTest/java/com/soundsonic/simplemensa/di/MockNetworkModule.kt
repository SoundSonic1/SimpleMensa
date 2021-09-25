package com.soundsonic.simplemensa.di

import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.HttpUrl
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.QueueDispatcher
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object MockNetworkModule {
    private const val SCHEME = "http"
    const val HOST = "localhost"
    const val PORT = 48542

    @Singleton
    @Provides
    fun provideMockWebServerUrl(): HttpUrl = HttpUrl.Builder()
        .scheme(SCHEME)
        .host(HOST)
        .port(PORT)
        .build()
        .resolve("/")!!

    @Provides
    fun provideDefaultDispatcher(): Dispatcher = QueueDispatcher().apply {
        setFailFast(MockResponse().setResponseCode(404))
    }

    @Provides
    fun provideMockWebServer(dispatcher: Dispatcher): MockWebServer = MockWebServer().apply {
        this.dispatcher = dispatcher
    }

    @Provides
    @Singleton
    fun provideOpenMensaApi(moshi: Moshi, url: HttpUrl): OpenMensaApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(url)
        .build()
        .create(OpenMensaApi::class.java)
}
