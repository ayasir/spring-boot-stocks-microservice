package com.kashsoftwares.stocks.dbservice.repository;

import com.kashsoftwares.stocks.dbservice.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {

    List<Quote> findByUserName(String useerName);
}
