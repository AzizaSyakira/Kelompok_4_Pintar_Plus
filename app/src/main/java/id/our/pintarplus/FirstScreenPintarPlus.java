package id.our.pintarplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FirstScreenPintarPlus extends AppCompatActivity {

    TextView Tv_ke_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_pintar_plus);

        // Menghubungkan TextView dengan ID di XML
        Tv_ke_Login = findViewById(R.id.Tv_ke_Login);

        // Menambahkan event listener untuk navigasi
        Tv_ke_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FirstScreenPintarPlus.this, "Navigating to Login Page", Toast.LENGTH_SHORT).show();
                // Intent untuk membuka LoginActivity
                Intent intent = new Intent(FirstScreenPintarPlus.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}


