package dev.guilhermepisco.msscbeerservice.web.model;

import dev.guilhermepisco.msscbeerservice.bootstrap.BeerLoader;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseTest {

    BeerDto getDto(){
        return BeerDto.builder()
                .beerName("BeerName")
                .beerStyle(BeerStyle.ALE)
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(BigDecimal.valueOf(11.2))
                .upc(BeerLoader.BEER_3_UPC)
                .build();
    }
}
