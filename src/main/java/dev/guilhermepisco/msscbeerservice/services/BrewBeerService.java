package dev.guilhermepisco.msscbeerservice.services;

import dev.guilhermepisco.msscbeerservice.config.JmsConfig;
import dev.guilhermepisco.msscbeerservice.domain.Beer;
import dev.guilhermepisco.msscbeerservice.events.BrewBeerEvent;
import dev.guilhermepisco.msscbeerservice.events.NewInventoryEvent;
import dev.guilhermepisco.msscbeerservice.repositories.BeerRepository;
import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerService {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.BREWERY_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event){
        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.getReferenceById(beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer {} : QOH: {}", beer.getMinOnHand(), beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);


    }

}
