package dev.guilhermepisco.msscbeerservice.events;

import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = -565054921024402492L;
    private BeerDto beerDto;

}
