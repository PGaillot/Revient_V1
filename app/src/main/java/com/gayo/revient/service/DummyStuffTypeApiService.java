package com.gayo.revient.service;

import com.gayo.revient.model.StuffType;

import java.util.List;

public class DummyStuffTypeApiService implements StuffTypeApiService {

    List<StuffType> stuffsTypes = StuffTypeGenerator.GenerateStuffTypes();

    @Override
    public List<StuffType> getStuffsTypes() {return stuffsTypes;}

    @Override
    public StuffType getStuffTypeById(String id) {
        StuffType stuffType = null;
        for (StuffType st :
                stuffsTypes) {
            if (st.getuId().equals(id)){
                stuffType = st;
            }
            if (stuffType == null){
                System.out.println("Error : no StuffType with the id : " + id);
            }
        }
        return stuffType;
    }
}
