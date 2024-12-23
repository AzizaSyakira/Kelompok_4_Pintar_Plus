package id.our.pintarplus;

import static id.our.pintarplus.R.id.webView;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayerActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        webView = findViewById(R.id.webView);

        String videoUrl = getIntent().getStringExtra("videoUrl");

        if (videoUrl != null) {
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);

            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(videoUrl);
        }
    }
}
