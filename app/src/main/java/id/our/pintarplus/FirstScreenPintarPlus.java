package id.our.pintarplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FirstScreenPintarPlus extends AppCompatActivity {

    private TextView Tv_ke_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_pintar_plus);

        // Temukan TextView dengan ID
        Tv_ke_Login = findViewById(R.id.Tv_ke_Login);

        // Set listener untuk navigasi
        Tv_ke_Login.setOnClickListener(v -> {
            Toast.makeText(FirstScreenPintarPlus.this, "Welcome To Pintar Plus", Toast.LENGTH_SHORT).show();
            try {
                // Intent untuk menuju LoginActivity
                Intent intent = new Intent(FirstScreenPintarPlus.this, LoginActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                // Tangani error jika LoginActivity tidak ditemukan
                Toast.makeText(FirstScreenPintarPlus.this, "Error: Unable to navigate to LoginActivity", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });

    }
}
