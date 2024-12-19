package id.our.pintarplus.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    // http://192.168.76.3:8000/api/
    // http://10.0.2.2:80/api/ --> emulator api memu
    // http://127.0.0.1:8000/api/
    // http://192.168.187.65:8000/api/
    private static final String BASE_URL = "http://10.0.2.2:80/API/api_pintarPlus.php?action="; // Sesuaikan BASE_URL

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
