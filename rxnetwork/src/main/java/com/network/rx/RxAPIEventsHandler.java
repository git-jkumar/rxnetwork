package com.network.rx;

import rx.functions.Action1;

/**
 * Created by ttnd on 15/11/16.
 */

public abstract class RxAPIEventsHandler<T> implements Action1<T> {

    public void call(T t){
        if(t instanceof RetroError)
            onError((RetroError)t);
        else
            onSuccess(t);
    }

    public abstract void onError(RetroError errorModel);

    public abstract void onSuccess(T t);
}
