package demosample.nirav.com.chooser;


import java.util.List;

import javax.inject.Inject;

import demosample.nirav.com.api.IAppWebAPI;
import demosample.nirav.com.base.BasePresenter;
import demosample.nirav.com.data.DataManager;
import demosample.nirav.com.utils.AppUtility;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by NiravJ
 */

public class AddressPresenter<V extends IAddressView> extends BasePresenter<V> implements
        IAddressPresenter<V> {
    private IAppWebAPI mApiService;

    @Inject
    public AddressPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }


    @Override
    public void getAddress(SelectDTO addressDTO,int mType) {
        switch (mType){
            case AppUtility.COUNTRY_TAG: fetchCountries(addressDTO.getLabel()); break;
            case AppUtility.STATE_TAG: fetchStates(addressDTO.getValue(), addressDTO.getLabel());
            break;
            case AppUtility.CITY_TAG: fetchCities(addressDTO.getValue(), addressDTO.getLabel());
            break;

        }
    }

    private void fetchCountries(String mCountryName){
        if (getMvpView().isNetworkConnected()) {
            if (mApiService == null)
                mApiService = provideNewApiService();
            Single<List<SelectDTO>> listSingle = mApiService.getCountries(mCountryName);
            listSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<SelectDTO>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onSuccess(List<SelectDTO> values) {
                            getMvpView().getValues(values);
                        }

                        @Override
                        public void onError(Throwable e) {
                            getMvpView().showthrow(e);
                        }
                    });
        }
    }

    private void fetchStates(int mCountryID, String mStateName){
        if (getMvpView().isNetworkConnected()) {
            if (mApiService == null)
                mApiService = provideNewApiService();
            Single<List<SelectDTO>> listSingle = mApiService.getStates(mCountryID,mStateName);
            listSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<SelectDTO>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onSuccess(List<SelectDTO> values) {
                            getMvpView().getValues(values);
                        }

                        @Override
                        public void onError(Throwable e) {
                            getMvpView().showthrow(e);
                        }
                    });
        }
    }

    private void fetchCities(int mStateID,String mCityName){
        if (getMvpView().isNetworkConnected()) {
            if (mApiService == null)
                mApiService = provideNewApiService();
            Single<List<SelectDTO>> listSingle = mApiService.getCities(mStateID,mCityName);
            listSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<SelectDTO>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onSuccess(List<SelectDTO> values) {
                            getMvpView().getValues(values);
                        }

                        @Override
                        public void onError(Throwable e) {
                            getMvpView().showthrow(e);
                        }
                    });
        }
    }


}
