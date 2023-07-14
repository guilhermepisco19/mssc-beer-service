package dev.guilhermepisco.msscbeerservice.web.controller;

import dev.guilhermepisco.msscbeerservice.web.model.BeerDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId){
        //todo impl
        return ResponseEntity.ok().body(BeerDto.builder().build());
    }

    @PostMapping
    public ResponseEntity<Void> saveNewBeer(@Valid @RequestBody BeerDto beerDto){
        //todo impl

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(beerDto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> saveNewBeer(@PathVariable UUID beerId,@Valid @RequestBody BeerDto beerDto){
        //todo impl
        return ResponseEntity.noContent().build();
    }
}
