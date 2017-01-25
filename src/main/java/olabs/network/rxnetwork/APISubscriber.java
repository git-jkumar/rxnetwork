package olabs.network.rxnetwork;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public class APISubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        // TODO To be evaluated
    }

    @Override
    public void onError(Throwable throwable) {
        RXEventBus.getInstance().post(getRetroError(throwable));
    }

    @Override
    public void onNext(T t) {
        RXEventBus.getInstance().post(t);
    }

    private RetroError getRetroError(Throwable throwable){

        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            Response response = httpException.response();
            return new RetroError(RetroError.Kind.HTTP, response.message(),response.code());
        }

        return throwable instanceof IOException?new RetroError(RetroError.Kind.NETWORK, throwable.getMessage(),-999): new RetroError(RetroError.Kind.UNEXPECTED, throwable.getMessage(), -999);

    }

}
