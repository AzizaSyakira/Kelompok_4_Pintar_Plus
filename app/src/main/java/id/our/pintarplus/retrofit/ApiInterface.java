package id.our.pintarplus.retrofit;

import java.util.List;

import id.our.pintarplus.models.CoursesModel;
import id.our.pintarplus.models.GradeModel;
import id.our.pintarplus.models.MatpelModel;
import id.our.pintarplus.models.VideoModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api_pintarPlus.php?action=getGrade")
    Call<List<GradeModel>> getGrade();

    @GET("api_pintarPlus.php?action=getMatpel")
    Call<List<MatpelModel>> getMatpel();

    @GET("api_pintarPlus.php?action=getCourse")
    Call<List<VideoModel>> getVideosByMatpel(@Query("matpelId") String matpelId);

}


