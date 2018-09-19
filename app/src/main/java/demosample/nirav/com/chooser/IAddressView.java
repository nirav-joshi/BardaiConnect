package demosample.nirav.com.chooser;


import java.util.List;

import demosample.nirav.com.base.BaseView;

/**
 * Created by Niravj on 3/14/2018.
 */

public interface IAddressView extends BaseView {

    void getValues(List<SelectDTO> list);
}
