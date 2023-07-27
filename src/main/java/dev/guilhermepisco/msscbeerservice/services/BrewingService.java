package dev.guilhermepisco.msscbeerservice.services;

import dev.guilhermepisco.msscbeerservice.domain.Beer;
import dev.guilhermepisco.msscbeerservice.events.BrewBeerEvent;
import dev.guilhermepisco.msscbeerservice.repositories.BeerRepository;
import dev.guilhermepisco.msscbeerservice.services.inventory.BeerInventoryService;
import dev.guilhermepisco.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.guilhermepisco.msscbeerservice.config.JmsConfig.BREWERY_REQUEST_QUEUE;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService inventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory(){
        var beers = getBeersWithLowInventory();

        beers.forEach(beer -> {
            jmsTemplate.convertAndSend(BREWERY_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
        });
    }

    private List<Beer> getBeersWithLowInventory() {
        return beerRepository.findAll()
                .stream()
                .filter(beer -> beer.getMinOnHand() >= inventoryService.getOnhandInventory(beer.getId()))
                .toList();
    }
}
