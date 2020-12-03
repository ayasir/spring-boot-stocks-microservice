package com.kashsoftwares.stocks.dbservice.rest;

import com.kashsoftwares.stocks.dbservice.entity.Quote;
import com.kashsoftwares.stocks.dbservice.entity.Quotes;
import com.kashsoftwares.stocks.dbservice.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceController {

    private QuotesRepository quotesRepository;

    @Autowired
    DbServiceController(QuotesRepository quotesRepository){
        this.quotesRepository = quotesRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQuotesByUserName(@PathVariable("username")
                                  final String username){
         return quotesRepository.findByUserName(username)
                .stream()
                 .map(Quote::getQuote)
                 .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes){

        quotes.getQuotes()
                .stream()
                .map(quote-> new Quote(quotes.getUserName(), quote))
                .forEach(quotesRepository::save);

        return getQuotesByUserName(quotes.getUserName());
    }

    @DeleteMapping("/delete/{username}")
    public List<String> deleteQuote(@PathVariable("username")
                               final String username){
        List<Quote> quotes = quotesRepository.findByUserName(username);
        quotesRepository.deleteAll(quotes);

        return getQuotesByUserName(username);
    }
}
