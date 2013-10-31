package com.cary.stq.service;

import com.cary.stq.to.Testitems;

import java.util.List;

public interface ITestItemsService {

    List<Testitems> search(Testitems testitems);

    List<Testitems> getItems();

}
