package demosample.nirav.com.chooser;


import demosample.nirav.com.base.MvpPresenter;

/**
 * Created by NiravJ
 */

public interface IAddressPresenter<V extends IAddressView>
        extends MvpPresenter<V> {
    void getAddress(SelectDTO addressDTO, int mType);
}
