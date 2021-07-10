package com.supermarket.project.service;

import java.util.List;

import com.supermarket.project.model.SuperMarket;

public interface SuperMarketService {
	
	List<SuperMarket> getAllMarkets();
	
	SuperMarket getMarketById(Long superMarketId);
	
	void addMarket(SuperMarket theSuperMarket);
	
	void deleteMarketById(Long superMarketId);

}
