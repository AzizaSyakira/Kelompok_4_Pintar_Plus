package id.our.pintarplus;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
            });
        RadioGroup toggleGroup = findViewById(R.id.toggleGroup);
        RadioButton rbUnread = findViewById(R.id.rbUnread);
        RadioButton rbAll = findViewById(R.id.rbAll);

        toggleGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbUnread) {
                rbUnread.setBackgroundResource(R.drawable.button_active);
                rbUnread.setTextColor(getResources().getColor(android.R.color.white));
                rbAll.setBackgroundResource(R.drawable.button_non_active);
                rbAll.setTextColor(getResources().getColor(android.R.color.black));
            } else if (checkedId == R.id.rbAll) {
                rbAll.setBackgroundResource(R.drawable.button_active);
                rbAll.setTextColor(getResources().getColor(android.R.color.white));
                rbUnread.setBackgroundResource(R.drawable.button_non_active);
                rbUnread.setTextColor(getResources().getColor(android.R.color.black));
            }
        });
    }
}



