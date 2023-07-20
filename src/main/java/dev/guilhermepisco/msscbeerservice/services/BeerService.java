package dev.guilhermepisco.msscbeerservice.services;

import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;

import java.util.UUID;


public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);
}
