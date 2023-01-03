package com.beldier.marvel.data.network

import com.beldier.marvel.PrivateKey
import com.beldier.marvel.PublicKey
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject

class QueryInterceptor @Inject constructor(@PrivateKey private val privateKey: String,@PublicKey private val publicKey: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val ts = Date().time
        val hash = generateHash(ts, privateKey, publicKey)

        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", hash)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }

}