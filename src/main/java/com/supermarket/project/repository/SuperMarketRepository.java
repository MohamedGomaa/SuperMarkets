package com.supermarket.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.project.model.SuperMarket;

public interface SuperMarketRepository extends JpaRepository<SuperMarket, Long> {

}
