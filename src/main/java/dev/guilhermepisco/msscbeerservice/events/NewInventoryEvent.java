package dev.guilhermepisco.msscbeerservice.events;

import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{
    @Serial
    private static final long serialVersionUID = 1029394920243896609L;

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
