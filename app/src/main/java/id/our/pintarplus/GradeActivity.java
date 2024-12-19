package id.our.pintarplus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.util.List;

import id.our.pintarplus.models.GradeModel;
import id.our.pintarplus.retrofit.ApiInterface;
import id.our.pintarplus.retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GradeActivity extends AppCompatActivity {

    private ImageView icon_1, icon_2, icon_3;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grade);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        icon_1 = findViewById(R.id.icon_1);
        icon_2 = findViewById(R.id.icon_2);
        icon_3 = findViewById(R.id.icon_3);

        // Initialize Retrofit
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        Retrofit retrofit = retrofitConfig.getRetrofitClientInstance();
        apiInterface = retrofit.create(ApiInterface.class);

        // Load grade data from API
        loadGradeData();

        // Set click listeners
        icon_1.setOnClickListener(v -> openMatpelActivity(1));
        icon_2.setOnClickListener(v -> openMatpelActivity(2));
        icon_3.setOnClickListener(v -> openMatpelActivity(3));
    }

    private void loadGradeData() {
        Call<List<GradeModel>> call = apiInterface.getGrade();
        call.enqueue(new Callback<List<GradeModel>>() {
            @Override
            public void onResponse(Call<List<GradeModel>> call, Response<List<GradeModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<GradeModel> grades = response.body();

                    // Display grades
                    if (grades.size() >= 3) {
                        loadImage(grades.get(0).icon, icon_1);
                        loadImage(grades.get(1).icon, icon_2);
                        loadImage(grades.get(2).icon, icon_3);
                    }
                } else {
                    Toast.makeText(GradeActivity.this, "Failed to load grades", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GradeModel>> call, Throwable t) {
                Log.e("error", t.getMessage().toString());
                Toast.makeText(GradeActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadImage(String imageUrl, ImageView imageView) {
        Glide.with(this)
                .load(imageUrl)
//                .placeholder(R.drawable.placeholder_image) // Optional placeholder image
//                .error(R.drawable.error_image) // Optional error image
                .into(imageView);
    }

    private void openMatpelActivity(int gradeId) {
        Intent intent = new Intent(GradeActivity.this, MatpelActivity.class);
        intent.putExtra("grade_id", gradeId);
        startActivity(intent);
    }
}
