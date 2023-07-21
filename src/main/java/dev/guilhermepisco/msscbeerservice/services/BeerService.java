package dev.guilhermepisco.msscbeerservice.services;

import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;
import dev.guilhermepisco.msscbeerservice.web.model.BeerPagedList;
import dev.guilhermepisco.msscbeerservice.web.model.BeerStyle;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;


public interface BeerService {

    BeerPagedList listBeers(String beerName, BeerStyle beerStyle, PageRequest pageRequest);
    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);
}
