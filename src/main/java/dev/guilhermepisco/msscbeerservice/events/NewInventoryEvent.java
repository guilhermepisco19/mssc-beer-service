package dev.guilhermepisco.msscbeerservice.events;

import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent{
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
