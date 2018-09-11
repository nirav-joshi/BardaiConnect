package demosample.nirav.com.utils.map;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * RotationLayout rotates the contents of the layout by multiples of 90 degrees.
 * <p/>
 * May not work with padding.
 */
public class RotationLayout extends FrameLayout {


    public RotationLayout(Context context) {
        super(context);
    }

    public RotationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RotationLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
/*
        canvas.translate(0, getHeight());
        canvas.rotate(JainConnectionUtility.ANGLE_270, getWidth() / JainConnectionUtility.CASE_2, 0);
        canvas.translate(getHeight() / JainConnectionUtility.CASE_2, -getWidth() / JainConnectionUtility.CASE_2);
*/

        super.dispatchDraw(canvas);
    }
}