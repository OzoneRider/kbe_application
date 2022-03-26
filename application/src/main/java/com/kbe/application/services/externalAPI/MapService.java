package com.kbe.application.services.externalAPI;

import com.kbe.application.models.externalAPI.GeoCode;
import com.kbe.application.models.storageAPI.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {

    private RestTemplate restTemplate;

    private final String URL = "https://nominatim.openstreetmap.org/search?q=";

    public MapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<GeoCode> getGeoCoordinates(Location location){
        String requestURL = queryBuilder(location);
        GeoCode[] coordinates = restTemplate.getForObject(requestURL, GeoCode[].class);

        if(coordinates == null || coordinates.length == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(coordinates[0], HttpStatus.OK);
    }

    private String queryBuilder(Location location){
        return URL+location.getHomeNr()+"+"+location.getStreet()+",+"
                +location.getCity()+",+"+location.getPostalCode()+",+"
                +location.getCountry()+"&format=json&polygon=1&addressdetails=1";
    }
}
