package demosample.nirav.com.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GalleryDTO implements Serializable{

    @SerializedName("galleryId")
    private String galleryId;
    @SerializedName("originalName")
    private String mOriginalName;
    @SerializedName("fileExtension")
    private String mFileExtension;
    @SerializedName("imageUrl")
    private String mImageUrl;

    public String getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(String galleryId) {
        this.galleryId = galleryId;
    }

    public String getmOriginalName() {
        return mOriginalName;
    }

    public void setmOriginalName(String mOriginalName) {
        this.mOriginalName = mOriginalName;
    }

    public String getmFileExtension() {
        return mFileExtension;
    }

    public void setmFileExtension(String mFileExtension) {
        this.mFileExtension = mFileExtension;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
