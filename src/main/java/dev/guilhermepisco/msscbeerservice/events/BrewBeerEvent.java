package dev.guilhermepisco.msscbeerservice.events;

import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
