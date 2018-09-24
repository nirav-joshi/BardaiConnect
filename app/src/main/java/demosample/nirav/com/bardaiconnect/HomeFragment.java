package demosample.nirav.com.bardaiconnect;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demosample.nirav.com.R;
import demosample.nirav.com.base.AbstractBaseFragment;
import demosample.nirav.com.di.component.ActivityComponent;
import demosample.nirav.com.utils.ToolbarUtil;

public class HomeFragment extends AbstractBaseFragment {
    private QuickAdapter adapter;

    @BindView(R.id.rv_quick_navigation)
    RecyclerView rvmenu;

    @Override
    public int getContentView() {
        return R.layout.newlandhome;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
        View toolbarView = ToolbarUtil.addRemoveViewFromToolbar(getActivity(), R.layout.toolbar_search_icon);
        ImageView imNotification = toolbarView.findViewById(R.id.img_notification);
        imNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        adapter = new QuickAdapter(getActivity());
        rvmenu.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,
                false));
        rvmenu.setAdapter(adapter);
        List<QuickButtons> strings = new ArrayList<>();
        strings.add(new QuickButtons("Family", R.drawable.ic_family));
        strings.add(new QuickButtons("Basic", R.drawable.ic_basic));
        strings.add(new QuickButtons("Lifestyle", R.drawable.ic_yoga));
        strings.add(new QuickButtons("Photos", R.drawable.ic_camera));
        strings.add(new QuickButtons("Education", R.drawable.ic_education));
        strings.add(new QuickButtons("Career", R.drawable.ic_career1));
        strings.add(new QuickButtons("Contact", R.drawable.ic_contact));
        strings.add(new QuickButtons("Desired Partner", R.drawable.ic_romance));

        adapter.addAll(strings);
    }
}
