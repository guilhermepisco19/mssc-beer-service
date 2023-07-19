package dev.guilhermepisco.msscbeerservice.web.mappers;

import dev.guilhermepisco.msscbeerservice.domain.Beer;
import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);

}