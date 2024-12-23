package id.our.pintarplus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

    private RecyclerView recycler_videos;
    private VideoAdapter videoAdapter;
    private ApiInterface apiInterface;
    private ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_matpel);

        recycler_videos = findViewById(R.id.recycler_videos);
        recycler_videos.setLayoutManager(new LinearLayoutManager(this));

        back_button = findViewById(R.id.back_button);

        back_button.setOnClickListener(view -> {
            Intent intent = new Intent(VideoMatpelActivity.this, ElementaryActivity.class);
            startActivity(intent);
        });

        Retrofit retrofit = new RetrofitConfig().getRetrofitClientInstance();
        apiInterface = retrofit.create(ApiInterface.class);

        String matpelId = getIntent().getStringExtra("matpel_id");

        if (matpelId != null) {
            loadVideos(matpelId);
        } else {
            Toast.makeText(this, "Matpel ID not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadVideos(String matpelId) {
        Call<List<VideoModel>> call = apiInterface.getVideosByMatpel(matpelId);
        call.enqueue(new Callback<List<VideoModel>>() {
            @Override
            public void onResponse(Call<List<VideoModel>> call, Response<List<VideoModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<VideoModel> videos = response.body();
                    videoAdapter = new VideoAdapter(VideoMatpelActivity.this, videos);
                    recycler_videos.setAdapter(videoAdapter);
                } else {
                    Toast.makeText(VideoMatpelActivity.this, "Failed to load videos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<VideoModel>> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                Toast.makeText(VideoMatpelActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
