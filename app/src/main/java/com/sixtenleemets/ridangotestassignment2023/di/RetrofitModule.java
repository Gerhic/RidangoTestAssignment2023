package com.sixtenleemets.ridangotestassignment2023.di;

import com.sixtenleemets.ridangotestassignment2023.data.network.TicketApiService;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.protobuf.ProtoConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    public Retrofit provideRetrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://your.api.base.url/") // FIXME Replace with API base URL
                .client(httpClient)
                .addConverterFactory(ProtoConverterFactory.create())
                .build();
    }

    @Provides
    public TicketApiService provideTicketApiService(Retrofit retrofit) {
        return retrofit.create(TicketApiService.class);
    }

}
