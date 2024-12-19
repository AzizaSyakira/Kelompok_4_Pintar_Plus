package id.our.pintarplus.retrofit;

import java.util.List;

import id.our.pintarplus.models.GradeModel;
import id.our.pintarplus.models.MatpelModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;

public class ApiInterface {
    @GET("getGrade")
    public Call<List<GradeModel>> getGrade(

    )
    {
        return null;
    }

    @GET("getMatpel")
    public Call<List<MatpelModel>> getMatpel(

    )
    {
        return null;
    }

}
