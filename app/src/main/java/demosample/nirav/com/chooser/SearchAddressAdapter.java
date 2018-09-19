package demosample.nirav.com.chooser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;

import demosample.nirav.com.R;
import demosample.nirav.com.utils.AppUtility;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by NiravJ
 *
 */

public class SearchAddressAdapter extends RecyclerView.Adapter<SearchAddressAdapter.AddressViewHolder> {
    private List<SelectDTO> addressList;
    private Context mContext;
    private PublishSubject<View> viewDetail = PublishSubject.create();


    public SearchAddressAdapter(Context context) {
        this.mContext = context;
        addressList = new ArrayList<>();
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.address_list_item, parent, false);
        RxView.clicks(view)
                .takeUntil(RxView.detaches(parent))
                .map(o -> view).subscribe(viewDetail);
        return new AddressViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AddressViewHolder holder, int position) {
        SelectDTO itemDto = addressList.get(position);
        if(!AppUtility.isEmptyString(itemDto.getLabel()))
        holder.txtName.setText(itemDto.getLabel().trim());

    }

    public Observable<View> asViewObservable() {
        return viewDetail.hide();
    }
    public List<SelectDTO> getAttachedList() {
        return addressList;
    }
    @Override
    public int getItemCount() {

            return addressList.size();
    }

    public void addAll(List<SelectDTO> lifePlanTasklist) {
        this.addressList = lifePlanTasklist;
        notifyDataSetChanged();
    }

    public void clear() {
        this.addressList.clear();
        notifyDataSetChanged();
    }



    class AddressViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;

        AddressViewHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.txt_label);
        }
    }
}
