package demosample.nirav.com.utils.custom_button;



public interface AnimatedButton {
    void startAnimation();
    void revertAnimation();
    void revertAnimation(final OnAnimationEndListener onAnimationEndListener);
    void dispose();
    void setProgress(int progress);
    void resetProgress();
}
