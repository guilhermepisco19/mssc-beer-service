package dev.guilhermepisco.msscbeerservice.services;

import dev.guilhermepisco.msscbeerservice.domain.Beer;
import dev.guilhermepisco.msscbeerservice.repositories.BeerRepository;
import dev.guilhermepisco.msscbeerservice.web.controller.exception.NotFoundException;
import dev.guilhermepisco.msscbeerservice.web.mappers.BeerMapper;
import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;
import dev.guilhermepisco.msscbeerservice.web.model.BeerPagedList;
import dev.guilhermepisco.msscbeerservice.web.model.BeerStyle;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService{

    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyle beerStyle, PageRequest pageRequest, boolean showInventoryOnHand) {

        Page<Beer> beerPage = getBeersPaged(beerName, beerStyle, pageRequest);

        if (showInventoryOnHand){
            return mapToBeerPageList(beerPage, beerMapper::beerToBeerDtoWithInventory);
        }
        else {
            return mapToBeerPageList(beerPage, beerMapper::beerToBeerDto);
        }

    }
    private Page<Beer> getBeersPaged(String beerName, BeerStyle beerStyle, PageRequest pageRequest) {
        Page<Beer> beerPage;

        if (StringUtils.hasText(beerName) && !ObjectUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (StringUtils.hasText(beerName) && ObjectUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (!StringUtils.hasText(beerName) && !ObjectUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }
        return beerPage;
    }

    private BeerPagedList mapToBeerPageList(Page<Beer> beerPage, Function<Beer,BeerDto> mapper) {
        BeerPagedList beerPagedList;
        beerPagedList = new BeerPagedList(beerPage
                .getContent()
                .stream()
                .map(mapper)
                .toList(),
                PageRequest
                        .of(beerPage.getPageable().getPageNumber(),
                                beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());
        return beerPagedList;
    }

    @Override
    public BeerDto getBeerById(UUID beerId, boolean showInventoryOnHand) {
        if(showInventoryOnHand){
            return beerMapper.beerToBeerDtoWithInventory(
                    beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
            );
        }
        else {
            return beerMapper.beerToBeerDto(
                    beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
            );
        }

    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer beer = beerMapper.beerDtoToBeer(beerDto);
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        beerRepository.save(beer);
    }
}
