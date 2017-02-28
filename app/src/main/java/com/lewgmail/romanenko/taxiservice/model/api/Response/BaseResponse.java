package com.lewgmail.romanenko.taxiservice.model.api.Response;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Lev on 14.11.2016.
 */

public class BaseResponse {
    @Nullable
    private Object mAnswer;

    private RequestResult mRequestResult;

    public BaseResponse() {
        mRequestResult = RequestResult.ERROR;
    }

    @NonNull
    public RequestResult getRequestResult() {
        return mRequestResult;
    }

    public BaseResponse setRequestResult(RequestResult requestResult) {
        mRequestResult = requestResult;
        return this;
    }

    @Nullable
    public <T> T getTypedAnswer() {
        if (mAnswer == null) {
            return null;
        }
        //noinspection unchecked
        return (T) mAnswer;
    }

    public BaseResponse setAnswer(@Nullable Object answer) {
        mAnswer = answer;
        return this;
    }

    public void save(Context context) {
    }
}
