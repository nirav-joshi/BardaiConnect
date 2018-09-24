package demosample.nirav.com.bardaiconnect;

class QuickButtons {
    private String quicknavigationName;
    private int quickIcon;

    public QuickButtons(String quicknavigationName, int quickIcon) {
        this.quicknavigationName = quicknavigationName;
        this.quickIcon = quickIcon;
    }

    public String getQuicknavigationName() {
        return quicknavigationName;
    }

    public int getQuickIcon() {
        return quickIcon;
    }
}
