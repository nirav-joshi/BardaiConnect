package demosample.nirav.com.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import demosample.nirav.com.bardaiconnect.R;
import demosample.nirav.com.data.DataManager;
import demosample.nirav.com.di.component.ActivityComponent;
import demosample.nirav.com.utils.custom_toast.Toasty;


public abstract class AbstractBaseFragment extends Fragment implements BaseView {
    protected View abstractView;
    @Inject
    DataManager mDataManager;
    private AbstractBaseActivity mActivity;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;
    private LayoutInflater inflater;
    private ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        abstractView = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this, abstractView);
        onViewReady(savedInstanceState);
        return abstractView;
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState) {
        resolveDaggerDependency();
        //To be used by child activities
    }

    protected void resolveDaggerDependency() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AbstractBaseActivity) {
            AbstractBaseActivity activity = (AbstractBaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    public AbstractBaseActivity getBaseActivity() {
        return mActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void showthrow(Throwable t) {
        hideDialog();
        showAlertDialog(t.getMessage());
    }

    @Override
    public void showErrorToast() {
        hideDialog();
        //showToast(getActivity().getString(R.string.some_error));
    }

    //Toasty message
    public void warningToast(CharSequence msg) {
        Toasty.warning(getActivity(), msg, Toast.LENGTH_SHORT, true).show();
    }

    public void errorToast(CharSequence msg) {
        Toasty.error(getActivity(), msg, Toast.LENGTH_SHORT, true).show();
    }

    public void successToast(CharSequence msg) {
        Toasty.success(getActivity(), msg, Toast.LENGTH_SHORT, true).show();
    }

    public void informationToast(CharSequence msg) {
        Toasty.info(getActivity(), msg, Toast.LENGTH_SHORT, true).show();
    }


    protected void showAlertDialog(String msg) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(null);
        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        dialogBuilder.setMessage(msg);
        dialogBuilder.setPositiveButton("Ok", (dialog, which) -> dialog.cancel());

        dialogBuilder.setCancelable(false);
        dialogBuilder.show();
    }



    @Override
    public void showProgressDialog(String title, @NonNull String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            if (title != null)
                mProgressDialog.setTitle(title);
            //mProgressDialog.setIcon(R.mipmap.ic_launcher);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }

    @Override
    public void hideDialog() {

        if (getActivity()!=null && !getActivity().isFinishing() && mProgressDialog != null &&
                mProgressDialog.isShowing
                ()) {
            mProgressDialog.dismiss();
        }
    }

    //Progress bar visibility

    public void showProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }


    @Override
    public void noInternetConnectionAvailable() {
        hideDialog();
        abstractView = inflater.inflate(R.layout.view_no_internet, container, false);
        Button btnRetry = abstractView.findViewById(R.id.btn_retry);
        btnRetry.setOnClickListener(view -> {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(AbstractBaseFragment.this);
            ft.attach(AbstractBaseFragment.this);
            ft.commit();
        });
        abstractView.invalidate();
    }


    protected void showToast(String mToastMsg) {
        hideDialog();
        Toast.makeText(getActivity(), mToastMsg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onDetach() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        hideDialog();
        mProgressDialog = null;
        super.onDetach();
    }

    public abstract int getContentView();

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}


