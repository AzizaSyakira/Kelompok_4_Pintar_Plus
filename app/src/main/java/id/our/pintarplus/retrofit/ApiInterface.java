package id.our.pintarplus.retrofit;

import java.util.List;

import id.our.pintarplus.models.GradeModel;
import id.our.pintarplus.models.MatpelModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiInterface {
    @GET("api_pintarPlus.php?action=getGrade")
    Call<List<GradeModel>> getGrade();

    @GET("api_pintarPlus.php?action=getMatpel")
    Call<List<MatpelModel>> getMatpel();
}

