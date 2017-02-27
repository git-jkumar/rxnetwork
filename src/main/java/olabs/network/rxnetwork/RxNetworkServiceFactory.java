package olabs.network.rxnetwork;

import java.util.HashMap;

import olabs.network.TokenAuthenticator;
import retrofit2.Retrofit;


public class RxNetworkServiceFactory {

    private static RxNetworkServiceFactory sDataService;
    private Retrofit mRestClient;
    private TokenAuthenticator mTokenAuthenticator;

    private RxNetworkServiceFactory() {
    }

    private RxNetworkServiceFactory(Retrofit restClient) {
        mRestClient = restClient;
    }

    public static <S> S getInstance(String baseUrl, Class<S> serviceClass,HashMap requestHeaderMap) {
        if (sDataService == null) {
            sDataService = new RxNetworkServiceFactory(RxNetworkClient.getRestAdapter(baseUrl, requestHeaderMap));
        }
        return sDataService.getClient(serviceClass);
    }

    public static <S> S getNewInstance(String baseUrl, Class<S> serviceClass,HashMap requestHeaderMap) {
            sDataService = null;
            sDataService = new RxNetworkServiceFactory(RxNetworkClient.getRestAdapter(baseUrl, requestHeaderMap));

        return sDataService.getClient(serviceClass);
    }


    private <S> S getClient(Class<S> serviceClass) {
        return mRestClient.create(serviceClass);
    }

}

