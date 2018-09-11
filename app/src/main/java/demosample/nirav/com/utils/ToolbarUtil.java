package demosample.nirav.com.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import demosample.nirav.com.bardaiconnect.R;


/**
 * Created by Mitesh on 3/1/2018.
 */

public class ToolbarUtil {

    public static View addRemoveViewFromToolbar(FragmentActivity fragmentActivity, int resourceId) {
        Toolbar toolbar = removeViewFromToolbar(fragmentActivity);
        if (resourceId == 0) {
            return null;
        } else {
            View view = LayoutInflater.from(fragmentActivity).inflate(resourceId, toolbar, false);
            toolbar.addView(view);
            return view;
        }
    }


    public static Toolbar removeViewFromToolbar(FragmentActivity fragmentActivity) {
        Toolbar toolbar = fragmentActivity.findViewById(R.id.toolbar);
        if (toolbar.getChildCount() > 1) {
            for (int i = 1; i <= toolbar.getChildCount(); i++) {
                toolbar.removeViewAt(1);
            }
        }
        return toolbar;
    }

    public static void setToolbarTitle(FragmentActivity fragmentActivity, @NonNull String name) {
        View view = addRemoveViewFromToolbar(fragmentActivity, R.layout.toolbar_title);
        if (view != null) {
            TextView textView = view.findViewById(R.id.txt_title);
            textView.setText(name);
        }
    }

}
