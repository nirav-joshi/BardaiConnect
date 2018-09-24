package demosample.nirav.com.bardaiconnect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import demosample.nirav.com.R;

class QuickAdapter extends RecyclerView.Adapter<QuickAdapter
        .SampleViewHolder> {

    private Context mContext;

    private List<QuickButtons> data;

    QuickAdapter(Context context) {
        this.mContext = context;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public QuickAdapter.SampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_quick_navigationhome, parent,
                false);
        return new QuickAdapter.SampleViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull QuickAdapter.SampleViewHolder holder, int position) {
        holder.txtName.setText(data.get(position).getQuicknavigationName());
        Glide.with(mContext)
                .load(data.get(position).getQuickIcon())
                .into(holder.ivIcon);

    }

    public void addAll(List<QuickButtons> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    public void clearAll() {
        this.data.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class SampleViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private AppCompatImageView ivIcon;
        SampleViewHolder(View view) {
            super(view);
            ivIcon = view.findViewById(R.id.img_icon);
            txtName = view.findViewById(R.id.tv_name);
        }
    }
}
