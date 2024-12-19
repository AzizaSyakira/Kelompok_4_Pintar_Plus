package id.our.pintarplus.retrofit;

import java.util.List;

import id.our.pintarplus.models.GradeModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;

public class ApiInterface {
    @GET("action=getGrade")
    Call<List<GradeModel>> getGrade(

    )
    {
        return null;
    }

    @GET("action=getMatpel")
    Call<List<GradeModel>> getMatpel(
            @Field("grade_id") int grade_id
    )
    {
        return null;
    }

}
