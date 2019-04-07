package by.tomal.exchangerates.data.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

fun providerApi() : GetData {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://nbrb.by/")
        .addConverterFactory(
            SimpleXmlConverterFactory.create()
        )
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(OkHttpClient())
        .build()

    return retrofit.create(GetData::class.java)
}