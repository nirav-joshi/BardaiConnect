package demosample.nirav.com.api;


import java.util.List;

import demosample.nirav.com.chooser.SelectDTO;
import demosample.nirav.com.login.LoginInfoDto;
import demosample.nirav.com.login.UpdatePasswordInfoDto;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IAppWebAPI {
    //Login
    @POST("Login")
    Single<Response<LoginInfoDto>> doLogin(@Body LoginInfoDto info);

    //Update password
    @PUT("Login/ChangePassword")
    Call<ResponseBody> changePassword(@Body UpdatePasswordInfoDto info);

    // RegisterJ
    @POST("Login/SignUpLearner")
    Call<ResponseBody> doRegister(@Body LoginInfoDto info);

    @POST("Login/ForgetPassword")
    Call<ResponseBody> forgetPassword(@Body LoginInfoDto dto);

    @PUT("Login/ChangePassword")
    Call<ResponseBody> changePassword(@Body LoginInfoDto dto);
    //OTP
    @POST("Login/VerifyOTP")
    Call<ResponseBody> verifyOtp(@Body LoginInfoDto dto);


    //LOcations
    @GET("Location/getCountries/{name}")
    Single<List<SelectDTO>> getCountries(@Path("name") String mCountryName);

    @GET("Location/getStatesByCountryId/{id}/{name}")
    Single<List<SelectDTO>> getStates(@Path("id") int mCountryID, @Path("name") String mStateName);

    @GET("Location/getCitysByStateId/{id}/{name}")
    Single<List<SelectDTO>> getCities(@Path("id") int mStateID, @Path("name") String
            mCityName);
}
