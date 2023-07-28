package dev.guilhermepisco.msscbeerservice.events;

import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {
    @Serial
    private static final long serialVersionUID = 1630062194729471321L;

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
