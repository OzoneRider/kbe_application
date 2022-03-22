package com.kbe.application.services.externalAPI;

import com.kbe.application.models.externalAPI.GeoCode;
import com.kbe.application.models.storageAPI.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {

    @Autowired
    RestTemplate restTemplate;

    private final String URL = "https://nominatim.openstreetmap.org/search?q=";


    public GeoCode getGeoCoordinates(Location location){
        String requestURL = queryBuilder(location);
        GeoCode[] array = restTemplate.getForObject(requestURL, GeoCode[].class);
        return array[0];
    }

    private String queryBuilder(Location location){
        return URL+location.getHomeNr()+"+"+location.getStreet()+",+"
                +location.getCity()+",+"+location.getPostalCode()+",+"
                +location.getCountry()+"&format=json&polygon=1&addressdetails=1";
    }
}
