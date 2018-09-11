package demosample.nirav.com.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Dipali on 5/12/2017
 * .
 */

public abstract class AbstractEndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    public static final String TAG = AbstractEndlessRecyclerViewScrollListener.class.getSimpleName();
    private int previousTotal;
    private boolean loading = true;
    private int currentPage = 1;

    private LinearLayoutManager mLinearLayoutManager;

    protected AbstractEndlessRecyclerViewScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();
        int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (loading && totalItemCount > previousTotal) {
            loading = false;
            previousTotal = totalItemCount;

        }
        int visibleThreshold = 2;
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold) &&
                totalItemCount >= AppConstants.DEFAULT_RECORD_SIZE) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);

}
