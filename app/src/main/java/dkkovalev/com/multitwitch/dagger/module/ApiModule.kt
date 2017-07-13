package dkkovalev.com.multitwitch.dagger.module

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dkkovalev.com.multitwitch.BuildConfig
import dkkovalev.com.multitwitch.MainApplication
import dkkovalev.com.multitwitch.data.TwitchApi
import dkkovalev.com.multitwitch.exception.NoConnectException
import dkkovalev.com.multitwitch.extensions.isConnected
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Singleton
import javax.net.ssl.SSLPeerUnverifiedException

@Module
class ApiModule {
    fun getOkHttpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })

    @Provides
    @Singleton
    fun providesOkHttpClient(app: MainApplication): OkHttpClient {
        val connectivityMAnager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return getOkHttpBuilder()
                .addInterceptor { chain ->
                    val requestBuilder = chain.request().newBuilder()
                    if (!connectivityMAnager.isConnected) {
                        throw NoConnectException
                    }

                    try {
                        chain.proceed(requestBuilder.build())
                    } catch (e: SocketTimeoutException) {
                        throw NoConnectException
                    } catch (e: UnknownHostException) {
                        throw NoConnectException
                    } catch (e: SSLPeerUnverifiedException) {
                        throw NoConnectException
                    }
                }.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): TwitchApi {
        val retrofit: Retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(okHttpClient)
                .build()
        return retrofit.create(TwitchApi::class.java)
    }
}
