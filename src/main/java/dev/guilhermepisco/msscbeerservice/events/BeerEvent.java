package dev.guilhermepisco.msscbeerservice.events;

import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = -565054921024402492L;
    private final BeerDto beerDto;

}
