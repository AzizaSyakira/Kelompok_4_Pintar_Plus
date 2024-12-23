package id.our.pintarplus;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.our.pintarplus.adapter.VideoAdapter;
import id.our.pintarplus.models.VideoModel;
import id.our.pintarplus.retrofit.ApiInterface;
import id.our.pintarplus.retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VideoMatpelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_matpel);

        // Initialize Retrofit
        Retrofit retrofit = new RetrofitConfig().getRetrofitClientInstance();
        apiInterface = retrofit.create(ApiInterface.class);

        // Get matpel_id from Intent
        String matpelId = getIntent().getStringExtra("matpel_id");

        if (matpelId != null) {
            // Load videos for the given matpel_id
            loadVideos(matpelId);
        } else {
            Toast.makeText(this, "Matpel ID not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadVideos(String matpelId) {
        // Memanggil API untuk mendapatkan video
        Call<List<VideoModel>> call = apiInterface.getVideosByMatpel(matpelId);
        call.enqueue(new Callback<List<VideoModel>>() {
            @Override
            public void onResponse(Call<List<VideoModel>> call, Response<List<VideoModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<VideoModel> videos = response.body();
                    videoAdapter = new VideoAdapter(videos);
                    recyclerView.setAdapter(videoAdapter);
                } else {
                    Log.e("API_ERROR", "Failed to load videos. Response: " + response.toString());
                    Toast.makeText(VideoMatpelActivity.this, "Failed to load videos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<VideoModel>> call, Throwable t) {
                Log.e("API_ERROR", "Failure: " + t.getMessage());
                Toast.makeText(VideoMatpelActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
