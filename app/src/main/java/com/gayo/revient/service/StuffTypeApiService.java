package com.gayo.revient.service;

import com.gayo.revient.model.StuffType;

import java.util.List;

public interface StuffTypeApiService {

    List<StuffType> getStuffsTypes();

    StuffType getStuffTypeById(String id);

}
