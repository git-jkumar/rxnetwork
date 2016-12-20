package com.network.rx;

import rx.Subscriber;

public class APISubscriber<T> extends Subscriber<T> {
    private T t;

    @Override
    public void onCompleted() {
        // TODO To be evaluated
    }

    @Override
    public void onError(Throwable throwable) {
        RXEventBus.getInstance().post(getDefaultError((RetrofitException) throwable));
    }

    @Override
    public void onNext(T t) {
        RXEventBus.getInstance().post(t);
    }

    public RetroError getDefaultError(RetrofitException retrofitException){
       return new RetroError(retrofitException.getKind(),retrofitException.getMessage(),retrofitException.getResponse().code());
    }
}
