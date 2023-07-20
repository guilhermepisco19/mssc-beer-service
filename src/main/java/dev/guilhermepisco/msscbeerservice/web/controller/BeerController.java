package dev.guilhermepisco.msscbeerservice.web.controller;

import dev.guilhermepisco.msscbeerservice.services.BeerService;
import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;


    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId){
        return ResponseEntity.ok().body(beerService.getBeerById(beerId));
    }

    @PostMapping
    public ResponseEntity<Void> saveNewBeer(@Valid @RequestBody BeerDto beerDto){

        BeerDto savedBeer = beerService.saveNewBeer(beerDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedBeer.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> saveNewBeer(@PathVariable UUID beerId,@Valid @RequestBody BeerDto beerDto){
        beerService.updateBeer(beerId, beerDto);
        return ResponseEntity.noContent().build();
    }
}
