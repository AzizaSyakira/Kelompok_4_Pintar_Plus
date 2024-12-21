package id.our.pintarplus;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

import id.our.pintarplus.models.MatpelModel;
import id.our.pintarplus.retrofit.ApiInterface;
import id.our.pintarplus.retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ElementaryActivity extends AppCompatActivity {

    private ImageView matpel_icon_1, matpel_icon_2, matpel_icon_3, matpel_icon_4, matpel_icon_5, matpel_icon_6;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementary);

        // Initialize views
        matpel_icon_1 = findViewById(R.id.matpel_icon_1);
        matpel_icon_2 = findViewById(R.id.matpel_icon_2);
        matpel_icon_3 = findViewById(R.id.matpel_icon_3);
        matpel_icon_4 = findViewById(R.id.matpel_icon_4);
        matpel_icon_5 = findViewById(R.id.matpel_icon_5);
        matpel_icon_6 = findViewById(R.id.matpel_icon_6);

        // Initialize Retrofit
        Retrofit retrofit = new RetrofitConfig().getRetrofitClientInstance();
        apiInterface = retrofit.create(ApiInterface.class);

        // Load matpel data from API
        loadMatpelData();
    }

    private void loadMatpelData() {
        Call<List<MatpelModel>> call = apiInterface.getMatpel();
        call.enqueue(new Callback<List<MatpelModel>>() {
            @Override
            public void onResponse(Call<List<MatpelModel>> call, Response<List<MatpelModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MatpelModel> matpelList = response.body();

                    // Check if the list size is enough to load images
                    if (matpelList.size() >= 6) {
                        loadImage(matpelList.get(0).icon, matpel_icon_1);
                        loadImage(matpelList.get(1).icon, matpel_icon_2);
                        loadImage(matpelList.get(2).icon, matpel_icon_3);
                        loadImage(matpelList.get(3).icon, matpel_icon_4);
                        loadImage(matpelList.get(4).icon, matpel_icon_5);
                        loadImage(matpelList.get(5).icon, matpel_icon_6);
                    } else {
                        Toast.makeText(ElementaryActivity.this, "Insufficient data from API", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ElementaryActivity.this, "Failed to load matpel data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MatpelModel>> call, Throwable t) {
                Log.e("API_ERROR", t.getMessage());
                Toast.makeText(ElementaryActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadImage(String imageData, ImageView imageView) {
        if (imageData.contains("/")) { // Deteksi jika data adalah Base64
            try {
                // Dekode string Base64 menjadi byte array
                byte[] decodedBytes = android.util.Base64.decode(imageData, android.util.Base64.DEFAULT);

                // Konversi byte array menjadi Bitmap
                Bitmap bitmap = android.graphics.BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

                if (bitmap != null) {
                    // Tampilkan gambar di ImageView
                    imageView.setImageBitmap(bitmap);
                } else {
                    // Jika decoding gagal, tampilkan placeholder
                    imageView.setImageResource(R.drawable.error);
                }
            } catch (Exception e) {
                e.printStackTrace();
                imageView.setImageResource(R.drawable.error); // Placeholder jika data tidak valid
            }
        } else {
            Glide.with(imageView.getContext())
                    .load(imageData)
                    .placeholder(R.drawable.image)
                    .error(R.drawable.error)
                    .into(imageView);
        }
    }   
}
