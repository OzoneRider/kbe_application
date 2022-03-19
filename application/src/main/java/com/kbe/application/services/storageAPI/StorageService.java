package com.kbe.application.services.storageAPI;

import com.kbe.application.models.storageAPI.DeliveryInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StorageService {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://kbe-storage/DeliveryInformation";

    public DeliveryInformation importDeliveryInformation(int productId){
        return restTemplate.getForObject(URL+"/"+productId, DeliveryInformation.class);
    }

    public DeliveryInformation exportDeliveryInformation(DeliveryInformation deliveryInformation){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<DeliveryInformation> request = new HttpEntity<DeliveryInformation>(deliveryInformation, headers);
        return restTemplate.postForObject(URL, request, DeliveryInformation.class);
    }


}
