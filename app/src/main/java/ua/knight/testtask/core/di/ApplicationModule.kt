package ua.knight.testtask.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.knight.testtask.BuildConfig
import ua.knight.testtask.data.repository.UsersNetworkRxRepository
import ua.knight.testtask.data.repository.UsersRxRepository
import javax.inject.Singleton

@Module
class ApplicationModule(private val application : Application) {

    @Provides @Singleton fun provideApplicationContext() : Context = application

    @Provides @Singleton fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient() : OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if(BuildConfig.DEBUG){
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }

        return okHttpClientBuilder.build()
    }

    @Provides @Singleton fun provideUsersRxRepository(dataSource : UsersNetworkRxRepository) : UsersRxRepository = dataSource
}