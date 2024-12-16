package id.our.pintarplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FirstScreenPintarPlus extends AppCompatActivity {
    TextView Tv_ke_Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first_screen_pintar_plus);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Menambahkan listener onClick untuk TextView
        findViewById(R.id.Tv_ke_Login).setOnClickListener(view -> navigateToLogin());
    }

    public void navigateToLogin() {
        Intent intent = new Intent(FirstScreenPintarPlus.this, LoginActivity.class);
        startActivity(intent);
    }
}
