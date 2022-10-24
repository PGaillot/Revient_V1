package com.gayo.revient.service;

import com.gayo.revient.model.Stuff;

import java.util.Date;
import java.util.List;

public interface StuffApiService {

    List<Stuff> getStuffs();

    void updateStuff(Stuff stuff);

    Date getReturnDate(Stuff stuff);

    long getReturnLoanCountInDays(Stuff stuff);

    int getPercentLoan(Stuff stuff);
}
