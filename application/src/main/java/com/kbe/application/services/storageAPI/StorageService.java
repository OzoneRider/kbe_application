package com.kbe.application.services.storageAPI;

import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.kbe.application.models.storageAPI.DeliveryInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class StorageService {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://localhost:4442/delivery-information";

    public DeliveryInformation importDeliveryInformation(int id){
        return restTemplate.getForObject(URL+"/"+id, DeliveryInformation.class);
    }

    public DeliveryInformation exportDeliveryInformation(DeliveryInformation info){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<DeliveryInformation> request = new HttpEntity<DeliveryInformation>(info, headers);
        return restTemplate.postForObject(URL, request, DeliveryInformation.class);
    }

    public DeliveryInformation updateDeliveryInformation(int id, DeliveryInformation info){
        String requestURL = URL+"/"+id;
        HttpEntity<DeliveryInformation> entity = new HttpEntity<>(info, restTemplate.headForHeaders(requestURL));
        try{
            ResponseEntity<DeliveryInformation> response = restTemplate.exchange(requestURL, HttpMethod.PUT, entity, DeliveryInformation.class);
            return response.getBody();
        }catch(HttpStatusCodeException e){
            e.getStatusCode();
        }
        return null;
    }


}
