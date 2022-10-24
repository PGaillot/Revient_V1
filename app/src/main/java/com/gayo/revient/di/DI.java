package com.gayo.revient.di;

import com.gayo.revient.service.DummyStuffApiService;
import com.gayo.revient.service.DummyStuffTypeApiService;
import com.gayo.revient.service.DummyUserApiService;
import com.gayo.revient.service.StuffApiService;
import com.gayo.revient.service.StuffTypeApiService;
import com.gayo.revient.service.UserApiService;

public class DI {

    private static final StuffApiService stuffService = new DummyStuffApiService();
    private static final UserApiService userService = new DummyUserApiService();
    private static final StuffTypeApiService stuffTypeApiService = new DummyStuffTypeApiService();

    public static StuffApiService getStuffApiService() {
        return stuffService;
    };

    public static StuffApiService getNewInstanceStuffApiService() {
        return new DummyStuffApiService();
    }

    public static UserApiService getNewInstanceUserApiService() {
        return new DummyUserApiService();
    }

    public static StuffTypeApiService getNewInstanceStuffTypeApiService() {
        return new DummyStuffTypeApiService();
    }

}
