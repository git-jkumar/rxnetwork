package com.network.rx;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohit Sharma on 09/02/16.
 */
public class ErrorMessage {

    @SerializedName("errorId")
    private int mErrorId;

    @SerializedName("errorMessage")
    private String mErrorMessage;

    public ErrorMessage() {
        mErrorMessage = "";
    }

    public int getErrorId() {
        return mErrorId;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorId(int errorCode) {
        this.mErrorId = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.mErrorMessage = errorMessage;
    }
}
