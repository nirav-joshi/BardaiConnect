package demosample.nirav.com.api;


import demosample.nirav.com.login.LoginInfoDto;
import demosample.nirav.com.login.UpdatePasswordInfoDto;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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


}
