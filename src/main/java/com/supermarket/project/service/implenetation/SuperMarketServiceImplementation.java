package com.supermarket.project.service.implenetation;

import java.util.List;

import javax.transaction.Transactional;

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

}
