package com.gayo.revient.di;

import com.gayo.revient.service.DummyStuffApiService;
import com.gayo.revient.service.StuffApiService;

public class DI {

    private static final StuffApiService service = new DummyStuffApiService();

    public static StuffApiService getStuffApiService() {
        return service;
    };

    public static StuffApiService getNewInstanceStuffApiService() {
        return new DummyStuffApiService();
    }

}
