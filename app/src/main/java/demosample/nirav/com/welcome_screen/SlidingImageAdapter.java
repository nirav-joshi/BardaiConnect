package demosample.nirav.com.welcome_screen;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demosample.nirav.com.R;


public class SlidingImageAdapter extends PagerAdapter {
    private ArrayList<Integer> IMAGES;
    private String[] messageArray;
    private String[] nameArray;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImageAdapter(Context context, List<Integer> images, String[] messageArray,String[] nameArray) {
        this.context = context;
        this.IMAGES = (ArrayList<Integer>) images;
        this.messageArray = messageArray;
        this.nameArray = nameArray;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.welcome_item_imageview, view, false);

        assert imageLayout != null;
        final ImageView imageView = imageLayout
                .findViewById(R.id.img_icons);
        final TextView txtMessage = imageLayout.findViewById(R.id.txt_message);
        final TextView txtName = imageLayout.findViewById(R.id.txt_name);
        txtMessage.setText(messageArray[position]);
        txtName.setText(nameArray[position]);
        imageView.setImageResource(IMAGES.get(position));
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
