package demosample.nirav.com.login;


import javax.inject.Inject;

import demosample.nirav.com.api.IAppWebAPI;
import demosample.nirav.com.base.BasePresenter;
import demosample.nirav.com.data.DataManager;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static demosample.nirav.com.utils.AppUtility.getValidStringMessage;


public class LoginPresenter<V extends ILoginView> extends BasePresenter<V> implements ILoginPresenter<V> {
    private IAppWebAPI mApiService;

    @Inject
    public LoginPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void doLogin(LoginInfoDto loginInfoDto) {
        if (getMvpView().isNetworkConnected()) {
            if (mApiService == null)
                mApiService = provideNewApiService();
            Single<Response<LoginInfoDto>> listSingle = mApiService.doLogin(loginInfoDto);
            listSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Response<LoginInfoDto>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(Response<LoginInfoDto> loginInfoDtoResponse) {
                            if (loginInfoDtoResponse.code() == 200 && loginInfoDtoResponse.body() != null)
                                getMvpView().login(loginInfoDtoResponse.body());
                            else
                                getMvpView().setErrorMessage(getValidStringMessage(loginInfoDtoResponse.errorBody()));
                        }

                        @Override
                        public void onError(Throwable e) {
                            getMvpView().showthrow(e);
                        }
                    });
        }
    }

    @Override
    public void doRegister(LoginInfoDto loginInfoDto) {
        if (getMvpView().isNetworkConnected()) {
            if (mApiService == null)
                mApiService = provideNewApiService();
            Call<ResponseBody> bodyCall = mApiService.doRegister(loginInfoDto);
            bodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        getMvpView().registerationCompleted(true);
                    } else if (response.errorBody() != null) {
                        getMvpView().setErrorMessage(getValidStringMessage(response.errorBody()));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    getMvpView().setErrorMessage("Something went Wrong");
                }
            });

        }
    }


    @Override
    public void doOTPVerification(LoginInfoDto loginInfoDto) {
        if (getMvpView().isNetworkConnected()) {
            if (mApiService == null)
                mApiService = provideNewApiService();
            Call<ResponseBody> bodyCall = mApiService.verifyOtp(loginInfoDto);
            bodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.code() == 200)
                        getMvpView().otpVerificationSucess();
                    else if (response.errorBody() != null) {
                        getMvpView().setErrorMessage(getValidStringMessage(response.errorBody()));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    getMvpView().setErrorMessage("Error while connecting server.");
                }
            });
        }
    }



    @Override
    public void doForgetPassword(String email) {
        LoginInfoDto dto = new LoginInfoDto();
        dto.setEmail(email);
        if (getMvpView().isNetworkConnected()) {
            if (mApiService == null)
                mApiService = provideNewApiService();
            Call<ResponseBody> bodyCall = mApiService.forgetPassword(dto);
            bodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.code() == 200)
                        getMvpView().forgetLinkSentSucess();
                    else if (response.errorBody() != null) {
                        getMvpView().setErrorMessage(getValidStringMessage(response.errorBody()));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    getMvpView().setErrorMessage("Error while connecting server.");
                }
            });
        }
    }


    @Override
    public void doResetPassWord(LoginInfoDto loginInfoDto) {
        if (getMvpView().isNetworkConnected()) {
            if (mApiService == null)
                mApiService = provideNewApiService();
            Call<ResponseBody> bodyCall = mApiService.changePassword(loginInfoDto);
            bodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.code() == 200)
                        getMvpView().setErrorMessage(null);
                    else if (response.errorBody() != null) {
                        getMvpView().setErrorMessage(getValidStringMessage(response.errorBody()));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    getMvpView().setErrorMessage("Error while connecting server.");
                }
            });
        }
    }

    @Override
    public void updatePassword(UpdatePasswordInfoDto updatePasswordInfoDto) {
        if(getMvpView().isNetworkConnected())
        {
            if(mApiService == null)
                mApiService = provideNewApiService();
            Call<ResponseBody> responseBodyCall = mApiService.changePassword(updatePasswordInfoDto);
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@android.support.annotation.NonNull Call<ResponseBody> call, @android.support.annotation.NonNull Response<ResponseBody> response) {
                    if(response.code() == 200)
                        getMvpView().getPasswordUpdateResponse(String.valueOf(1));
                    else if (response.errorBody() != null) {
                        getMvpView().getPasswordUpdateResponse(getValidStringMessage(response.errorBody()));
                    }
                }

                @Override
                public void onFailure(@android.support.annotation.NonNull Call<ResponseBody> call, @android.support.annotation.NonNull Throwable t) {

                }
            });
        }
    }
}
