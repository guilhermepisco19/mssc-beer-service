package dev.guilhermepisco.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class BeerDtoTest extends BaseTest{

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeDto() throws JsonProcessingException {
        BeerDto beerDto = getDto();

        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);
    }

    @Test
    void testDeserialize() throws JsonProcessingException {
        String json = "{\"id\":\"1f17fa6a-7912-42e7-95f9-3a6ec07ed9b6\",\"version\":null,\"createdDate\":\"2023-07-20T17:07:10.647921+01:00\",\"lastModifiedDate\":\"2023-07-20T17:07:10.647921+01:00\",\"beerName\":\"BeerName\",\"beerStyle\":\"ALE\",\"upc\":100,\"price\":11.2}";

        BeerDto dto = objectMapper.readValue(json, BeerDto.class);

        System.out.println(dto);
    }

}