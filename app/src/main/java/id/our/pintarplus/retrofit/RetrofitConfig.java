package id.our.pintarplus.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    // http://10.0.2.2:80/api/ --> emulator api memu
    // http://192.168.238.162:80/api/ -->WIFI UNIQUE api memu
    // http://172.31.96.1:80/api/ -->WIFI HP api memu
    // http://192.168.192.162/api/ -->WIFI HP 2 api memu

    private static final String BASE_URL = "http://192.168.192.162:80/API/"; // Sesuaikan BASE_URL

    public Retrofit getRetrofitClientInstance() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();
    }
}
