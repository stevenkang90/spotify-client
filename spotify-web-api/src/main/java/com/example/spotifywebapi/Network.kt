package com.example.spotifywebapi

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.File
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object Network {
    private const val TIMEOUT_VALUE = 15L
    private const val LONG_TIMOUT_VALUE = 45L

    const val USER_AGENT = "MySkyApp/Android/${BuildConfig.VERSION_NAME}"

    val httpClient = newOkHttpClient()

    private val defaultServiceClient = addDefaultHeader()
    private val longTimeoutServiceClient = addLongTimeoutHeader()

    private val jacksonFactory: JacksonConverterFactory = JacksonConverterFactory.create(ignoreUnknownObjectMapper())

    private val rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create()

    lateinit var retrofit: Retrofit
        private set


    fun <S> createService(serviceClass: Class<S>, baseUrl: String = "https://api.spotify.com", longTimeout: Boolean = false): S {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(jacksonFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .client(if (longTimeout) longTimeoutServiceClient else defaultServiceClient)
                .build()

        return retrofit.create(serviceClass)
    }

    private fun buildHTTPHeaders(timeoutValue: Long): OkHttpClient.Builder {
        val httpBuilder = httpClient.newBuilder()

        val interceptors = mutableListOf<okhttp3.Interceptor>()
        interceptors.add(customUserAgent())

        httpBuilder.readTimeout(timeoutValue, TimeUnit.SECONDS).connectTimeout(timeoutValue, TimeUnit.SECONDS)



        httpBuilder.interceptors().addAll(interceptors)

        return httpBuilder
    }


    private fun customUserAgent() =
            Interceptor { chain ->
                val newRequest = chain.request().newBuilder()
                        .addHeader("User-Agent", USER_AGENT).build()
                chain.proceed(newRequest)
            }

    private fun addDefaultHeader(): OkHttpClient {
        return buildHTTPHeaders(TIMEOUT_VALUE).build()
    }

    private fun addLongTimeoutHeader(): OkHttpClient {
        return buildHTTPHeaders(LONG_TIMOUT_VALUE).build()
    }

    private fun trustAnythingSocketFactory(): SSLSocketFactory {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustEveryoneManager(), SecureRandom())
        return sslContext.socketFactory
    }

    private fun trustEveryoneManager(): Array<out TrustManager>? {
        return arrayOf(object : javax.net.ssl.X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<out X509Certificate>? {
                return arrayOf()
            }
        })
    }

    private fun newOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        return builder.build()
    }
}
fun ignoreUnknownObjectMapper(): ObjectMapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
