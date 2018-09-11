
package demosample.nirav.com.utils;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class GeneralSearchDTO {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("categories")
    private String mCategories;
    @SerializedName("distance")
    private double mDistance;
    @SerializedName("duration")
    private int mDuration;
    @SerializedName("fromDate")
    private String mFromDate;
    @SerializedName("fromTime")
    private String mFromTime;
    @SerializedName("id")
    private int mId;
    @SerializedName("image")
    private String mImage;
    private double latitude;
    private double longitude;
    private int srNo;
    @SerializedName("name")
    private String mName;
    @SerializedName("percentage")
    private double mPercentage;
    @SerializedName("price")
    private double mPrice;
    @SerializedName("rating")
    private float mRating;
    @SerializedName("recordType")
    private int mRecordType;
    @SerializedName("review")
    private int mReview;
    @SerializedName("sortOrder")
    private int mSortOrder;
    @SerializedName("toDate")
    private String mToDate;
    @SerializedName("toTime")
    private String mToTime;
    @SerializedName("liveEvent")
    private boolean isLive;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    private String nearByCategoryName;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCategories() {
        return mCategories;
    }

    public void setCategories(String categories) {
        mCategories = categories;
    }

    public double getDistance() {
        return mDistance;
    }

    public void setDistance(double distance) {
        mDistance = distance;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public String getFromDate() {
        return mFromDate;
    }

    public void setFromDate(String fromDate) {
        mFromDate = fromDate;
    }

    public String getFromTime() {
        return  mFromTime;
    }

    public void setFromTime(String fromTime) {
        mFromTime = fromTime;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getPercentage() {
        return mPercentage;
    }

    public void setPercentage(double percentage) {
        mPercentage = percentage;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float rating) {
        mRating = rating;
    }

    public int getRecordType() {
        return mRecordType;
    }

    public void setRecordType(int recordType) {
        mRecordType = recordType;
    }

    public int getReview() {
        return mReview;
    }

    public void setReview(int review) {
        mReview = review;
    }

    public int getSortOrder() {
        return mSortOrder;
    }

    public void setSortOrder(int sortOrder) {
        mSortOrder = sortOrder;
    }

    public String getToDate() {
        return mToDate;
    }

    public void setToDate(String toDate) {
        mToDate = toDate;
    }

    public String getToTime() {
        return  mToTime;
    }

    public void setToTime(String toTime) {
        mToTime = toTime;
    }

    public String getNearByCategoryName() {
        return nearByCategoryName;
    }

    public void setNearByCategoryName(String nearByCategoryName) {
        this.nearByCategoryName = nearByCategoryName;
    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }
}
