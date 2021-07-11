package com.supermarket.project.service.implenetation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.supermarket.project.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.project.model.SuperMarket;
import com.supermarket.project.repository.SuperMarketRepository;
import com.supermarket.project.service.SuperMarketService;

@Service
@Transactional
public class SuperMarketServiceImplementation implements SuperMarketService {

	@Autowired
	SuperMarketRepository theMarketRepository;

	@Override
	public List<SuperMarket> getAllMarkets() {
		return theMarketRepository.findAll();
	}

	@Override
	public SuperMarket getMarketById(Long superMarketId) {
		Optional<SuperMarket> theOptiona
		return theMarketRepository.getById(superMarketId);
	}

	@Override
	public void addMarket(SuperMarket theSuperMarket) {
		theMarketRepository.save(theSuperMarket);
	}

	@Override
	public void deleteMarketById(Long superMarketId) {
		theMarketRepository.deleteById(superMarketId);
	}

	public Set<Item> getMarketPriceList(Long MarketId){
		SuperMarket theMarket =
	}

}
