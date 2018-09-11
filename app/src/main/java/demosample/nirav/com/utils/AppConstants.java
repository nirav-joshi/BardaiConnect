package demosample.nirav.com.utils;

public class AppConstants {
    private AppConstants() {
        throw new IllegalAccessError("Access Denied");
    }

    public enum Connect {
        Requested(1),
        Connected(2),
        Disconnected(3);

        private final int value;

        Connect(final int newValue) {
            value = newValue;
        }

        public int getValue() {
            return value;
        }
    }

    public static final int DEFAULT_RECORD_SIZE = 20;
    public static final int SEARCH_BUSINESS = 3;
    public static final int LOGIN_USER = 1;
    public static final int SEARCH_TEMPLE = 5;
    public static final int SEARCH_COACH = 1;
    public static final int REQUEST_CREATED = 201;
    public static final String SELECTED_VALUE = "Value";
    public static final String SEARCH_FILTER = "search_filter";
    public static final String YES_NO = "Yes/No";
    public static final String SINGLE_COMBO = "Single Combo";
    public static final String MULTI_COMBO = "Multi Combo";
    public static final int REQUESTCHECKSETTINGS = 100;




    //home page slider
    public static final String HOME_SLIDER_POOJA = "pooja";
    public static final String HOME_SLIDER_BUSINESS = "business";
    public static final String HOME_SLIDER_SHOP = "shop";
}
