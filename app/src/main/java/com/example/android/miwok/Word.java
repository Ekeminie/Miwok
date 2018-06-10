package com.example.android.miwok;

/**
 * Created by Delight on 01/04/2018.
 */

public class Word {
    private int mAudioResourceId;

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;


    public Word(String mDefaultTranslation, String mMiwokTranslation, int mauidoResource) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        mAudioResourceId = mauidoResource;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int imageResourceId, int mauidoResouce) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResourceId = imageResourceId;
        mAudioResourceId = mauidoResouce;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }
}