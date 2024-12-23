package id.our.pintarplus.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.our.pintarplus.R;
import id.our.pintarplus.VideoPlayerActivity;
import id.our.pintarplus.models.VideoModel;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context context;
    private List<VideoModel> videoList;

    public VideoAdapter(Context context, List<VideoModel> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoModel video = videoList.get(position);

        holder.videoTitle.setText(video.getJudul());
        Glide.with(context)
                .load(getYouTubeThumbnailUrl(video.getLink()))
                .placeholder(R.drawable.error)
                .into(holder.videoThumbnail);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoPlayerActivity.class);
            intent.putExtra("videoUrl", video.getLink());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    private String getYouTubeThumbnailUrl(String videoUrl) {
        String videoId = null;
        if (videoUrl.contains("v=")) {
            videoId = videoUrl.split("v=")[1];
            int ampersandIndex = videoId.indexOf('&');
            if (ampersandIndex != -1) {
                videoId = videoId.substring(0, ampersandIndex);
            }
        } else if (videoUrl.contains("youtu.be/")) {
            videoId = videoUrl.split("youtu.be/")[1];
        }
        return "https://img.youtube.com/vi/" + videoId + "/0.jpg";
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView videoTitle;
        ImageView videoThumbnail;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoTitle = itemView.findViewById(R.id.video_title);
            videoThumbnail = itemView.findViewById(R.id.video_thumbnail);
        }
    }
}
