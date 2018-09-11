package demosample.nirav.com.utils.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import demosample.nirav.com.bardaiconnect.R;


public class IconGenerator {


    private static final int STYLE_DEFAULT = 1;
    private static final int STYLE_WHITE = 2;
    private static final int STYLE_RED = 3;
    private static final int STYLE_BLUE = 4;
    private static final int STYLE_GREEN = 5;
    private static final int STYLE_PURPLE = 6;
    private static final int STYLE_ORANGE = 7;
    private final Context mContext;
    private BubbleDrawable mBackground;
    private ViewGroup mContainer;
    private TextView mTextView;

    public IconGenerator(Context context) {
        mContext = context;
        mBackground = new BubbleDrawable(mContext.getResources());
        mContainer = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.amu_text_bubble, null);
        RotationLayout mRotationLayout = (RotationLayout) mContainer.getChildAt(0);
        mTextView = mRotationLayout.findViewById(R.id.amu_text);
        setStyle();
    }

    private static int getStyleColor(int style) {
        switch (style) {

            case STYLE_RED:
                return 0xffcc0000;
            case STYLE_BLUE:
                return 0xff0099cc;
            case STYLE_GREEN:
                return 0xff669900;
            case STYLE_PURPLE:
                return 0xff9933cc;
            case STYLE_ORANGE:
                return 0xffff8800;
            case STYLE_DEFAULT:
            case STYLE_WHITE:
            default:
                return 0xffffffff;
        }
    }

    private static int getTextStyle(int style) {
        switch (style) {
            case STYLE_RED:
            case STYLE_BLUE:
            case STYLE_GREEN:
            case STYLE_PURPLE:
            case STYLE_ORANGE:
                return R.style.amu_Bubble_TextAppearance_Light;
            case STYLE_DEFAULT:
            case STYLE_WHITE:
            default:
                return R.style.amu_Bubble_TextAppearance_Dark;
        }
    }

    public MarkerOptions createMarker(double latitude, double longitude, String text) {
        LatLng latLng = new LatLng(latitude, longitude);
        float mAnchorV = 1F;
        float mAnchorU = 0.5F;
        return new MarkerOptions().
                icon(BitmapDescriptorFactory.fromBitmap(makeIcon(text))).
                position(latLng).
                anchor(mAnchorU, mAnchorV);
    }

    private Bitmap makeIcon(CharSequence text) {
        if (mTextView != null) {
            mTextView.setText(text);
        }

        return makeIcon();
    }

    private Bitmap makeIcon() {
        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mContainer.measure(measureSpec, measureSpec);
        int measuredWidth = mContainer.getMeasuredWidth();
        int measuredHeight = mContainer.getMeasuredHeight();
        mContainer.layout(0, 0, measuredWidth, measuredHeight);
        Bitmap r = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        r.eraseColor(Color.TRANSPARENT);
        Canvas canvas = new Canvas(r);
        mContainer.draw(canvas);
        return r;
    }

    private void setStyle() {
        setColor(getStyleColor(IconGenerator.STYLE_RED));
        setTextAppearance(mContext, getTextStyle(IconGenerator.STYLE_RED));
    }

    private void setColor(int color) {
        mBackground.setColor(color);
        setBackground(mBackground);
    }

    @SuppressWarnings("deprecation")
    // View#setBackgroundDrawable is compatible with pre-API level 16 (Jelly Bean).
    private void setBackground(Drawable background) {
        mContainer.setBackgroundDrawable(background);

        // Force setting of padding.
        // setBackgroundDrawable does not call setPadding if the background has 0 padding.
        if (background != null) {
            Rect rect = new Rect();
            background.getPadding(rect);
            mContainer.setPadding(rect.left, rect.top, rect.right, rect.bottom);
        } else {
            mContainer.setPadding(0, 0, 0, 0);
        }
    }

    private void setTextAppearance(Context context, int resid) {
        if (mTextView != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mTextView.setTextAppearance(resid);
            } else
                mTextView.setTextAppearance(context, resid);
        }
    }

}
