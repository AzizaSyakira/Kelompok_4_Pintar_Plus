package id.our.pintarplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    private TextView sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Adjust padding for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Button to navigate to Sign Up page
        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, GradeActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btndaftar).setOnClickListener(v -> {
            Toast.makeText(this, "Buat Akun!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

    }
}
