package com.network.rx;

import java.net.UnknownHostException;

import rx.Subscriber;

/**
 * Created by ttnd on 20/10/16.
 */
public class APISubscriber<T> extends Subscriber<T> {
    private T t;

    @Override
    public void onCompleted() {
        // TODO To be evaluated
    }

    @Override
    public void onError(Throwable throwable) {
        RetrofitException error = (RetrofitException) throwable;
        if (error.getKind() == RetrofitException.Kind.HTTP) {
            if (error.getResponse().code() == 401) {
                RXEventBus.getInstance().post(getSessionTimeoutError());
            } else {
                RXEventBus.getInstance().post(getDefaultError());
            }

        } else if (error.getKind() == RetrofitException.Kind.NETWORK) {

                if (throwable instanceof UnknownHostException)
                    RXEventBus.getInstance().post(getErrorMessage("There is no Internet connection. Please try again later"));
                else
                    RXEventBus.getInstance().post(getErrorMessage("There is no Internet connection. Please try again later"));
        }
    }

    @Override
    public void onNext(T t) {

        RXEventBus.getInstance().post(t);
    }


    public ErrorMessage getDefaultError(){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorId(00000);
        errorMessage.setErrorMessage("Some error occurred...try after sometime");
        return errorMessage;
    }

    public ErrorMessage getErrorMessage(String msg){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorId(00000);
        errorMessage.setErrorMessage(msg);
        return errorMessage;
    }

    public ErrorMessage getSessionTimeoutError(){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorId(401);
        errorMessage.setErrorMessage("Your session has expired...please login again");
        return errorMessage;
    }

}
