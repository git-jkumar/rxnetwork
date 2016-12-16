package com.network.rx;

import java.util.HashMap;

import retrofit2.Retrofit;


public class ServiceFactory {

    private static ServiceFactory sDataService;
    private Retrofit mRestClient;

    private ServiceFactory() {
    }

    private ServiceFactory(Retrofit restClient) {
        mRestClient = restClient;
    }

    public static <S> S getInstance(String baseUrl, Class<S> serviceClass,HashMap requestHeaderMap) {
        if (sDataService == null) {
            sDataService = new ServiceFactory(NetworkClient.getRestAdapter(baseUrl, requestHeaderMap));
        }
        return sDataService.getClient(serviceClass);
    }

    public static <S> S getNewInstance(String baseUrl, Class<S> serviceClass,HashMap requestHeaderMap) {
            sDataService = null;
            sDataService = new ServiceFactory(NetworkClient.getRestAdapter(baseUrl, requestHeaderMap));

        return sDataService.getClient(serviceClass);
    }


    private <S> S getClient(Class<S> serviceClass) {
        return mRestClient.create(serviceClass);
    }

}

