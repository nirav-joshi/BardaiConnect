package demosample.nirav.com.utils;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import okhttp3.ResponseBody;


public final class AppUtility {

    public static final String IMAGE_URL = "http://192.168.1.5/";
    public static final String IMAGE_ORIGNAL_URL = IMAGE_URL + "JainImage/Original/";
    public static final String IMAGE_THUMB_URL = IMAGE_URL + "JainImage/Thumbnail/";
    public static final String EXTRA_FRAGMENT_TAG = "Fragment";

    public static int calculateAge(Calendar birthDay) {
        int years = 0;
        int months = 0;
        int days = 0;
        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);
        //Get difference between years
        years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        int currMonth = now.get(Calendar.MONTH) + 1;
        int birthMonth = birthDay.get(Calendar.MONTH) + 1;
        //Get difference between months
        months = currMonth - birthMonth;
        //if month difference is in negative then reduce years by one and calculate the number of months.
        if (months < 0) {
            years--;
            months = 12 - birthMonth + currMonth;
            if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                months--;
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            years--;
            months = 11;
        }
        //Calculate the days
        if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
            days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
        else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            int today = now.get(Calendar.DAY_OF_MONTH);
            now.add(Calendar.MONTH, -1);
            days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
        } else {
            days = 0;
            if (months == 12) {
                years++;
                months = 0;
            }
        }
        //Create new Age object
        return years;
    }


    public enum ReviewsSorting {
        LATEST, OLDEST, HIGHEST, LOWEST
    }


    //Profile related changes
    public static final int COUNTRY_TAG = 1;
    public static final int STATE_TAG = 2;
    public static final int CITY_TAG = 3;
    public static final int PINCODE_TAG = 4;
    public static final int AFTER_TEXTED_CHANGE_QUANTUM = 1;
    public static final int AFTER_TEXTED_CHANGE_QUANTUM_MILI = 150;

    //Rotation Angles
    public static final int ANGLE_270 = 270;

    public static int openNextFragment = 0;

    public static String GOALDATE = "2018-08-01";


    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    /**
     * @param target charsequence to validate
     * @return True/False accordingly
     */
    public static boolean isValidEmail(CharSequence target) {
        if (null == target || target.length() == 0) {
            return false;
        }
        Pattern emailPattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher emailMatcher = emailPattern.matcher(target);
        return emailMatcher.matches();
    }

    /**
     * @param context pass the activity.
     */
    public static void hideSoftInputKeyboard(Activity context) {
        // Check if no view has focus:
        View view = context.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void setProgressDialogAnimation(ProgressBar mProgressBar, int process) {
        ObjectAnimator animation = ObjectAnimator.ofInt(mProgressBar, "progress", 0, process);
        animation.setDuration(1000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }

    public static boolean isValidPhone(CharSequence target) {
        if (null == target || target.length() == 0) {
            return false;
        }
        Pattern emailPattern = Pattern

                .compile("^[2-9]{2}[0-9]{8}$");
        Matcher emailMatcher = emailPattern.matcher(target);
        return emailMatcher.matches();
    }

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<>();

        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    private static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    private static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    public static String getValidStringMessage(ResponseBody data) {
        JSONObject jObjError;
        try {
            jObjError = new JSONObject(data.string());
            return jObjError.getString("Message").replaceAll("\\[\"", "")
                    .replaceAll("\"\\]", "");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String isLockedUser(ResponseBody data) {
        JSONObject jObjError;
        try {
            jObjError = new JSONObject(data.string());
            return jObjError.getString("lock").replaceAll("\\[\"", "")
                    .replaceAll("\"\\]", "");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }




    private static <T> Consumer<T> splitBy(
            Predicate<T> condition,
            Consumer<T> action1,
            Consumer<T> action2,
            T zero) {
        return n -> {
            if (condition.test(n)) {
                action1.accept(n);
                action2.accept(zero);
            } else {
                action1.accept(zero);
                action2.accept(n);
            }
        };
    }



    public static boolean isEmptyString(String data) {
        return data == null || data.trim().isEmpty();
    }

    public static boolean isNotEmptyString(String data) {
        return !isEmptyString(data);
    }

    public static boolean isNotEmptyList(List<?> list) {
        return (list != null && !list.isEmpty());
    }

    public static boolean isEmptyList(List<?> list) {
        return !isNotEmptyList(list);
    }


    public static boolean isValidName(String word) {
        return word.matches("[a-zA-Z.? ]*");
    }
}
