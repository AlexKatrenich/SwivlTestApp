package com.katrenich.testapp.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.katrenich.testapp.data.api.GitHubApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

	@Provides
	@Singleton
	fun provideGson(): Gson = GsonBuilder().create()

	@Provides
	@Singleton
	fun providesHttpOkClient(): OkHttpClient =
		OkHttpClient
			.Builder()
			.apply {
				connectTimeout(30, TimeUnit.SECONDS)
				readTimeout(30, TimeUnit.SECONDS)
				writeTimeout(30, TimeUnit.SECONDS)
			}
			.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
			.build()

	@Provides
	@Singleton
	fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
		Retrofit.Builder()
			.baseUrl("https://api.github.com/")
			.addConverterFactory(GsonConverterFactory.create(gson))
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.client(okHttpClient)
			.build()


	@Provides
	@Singleton
	fun provideApi(retrofit: Retrofit): GitHubApi =
		retrofit.create(GitHubApi::class.java)

}