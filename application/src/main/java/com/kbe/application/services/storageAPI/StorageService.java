package com.kbe.application.services.storageAPI;

import com.kbe.application.exceptions.DeliveryInformationNotFoundException;
import com.kbe.application.models.storageAPI.DeliveryInformation;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@CommonsLog
public class StorageService {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://localhost:4442/delivery-information";

    public DeliveryInformation importDeliveryInformation(int id) throws DeliveryInformationNotFoundException{
        DeliveryInformation info = restTemplate.getForObject(URL+"/"+id, DeliveryInformation.class);

        if(info == null)
            throw new DeliveryInformationNotFoundException(id);

        return info;

    }

    public ResponseEntity<?> exportDeliveryInformation(DeliveryInformation info){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<DeliveryInformation> entity = new HttpEntity<DeliveryInformation>(info, headers);

        try{
            ResponseEntity<DeliveryInformation> response = restTemplate.exchange(URL, HttpMethod.POST, entity, DeliveryInformation.class);
            return response;
        }catch (HttpStatusCodeException e){
            log.error(e.getMessage());
            log.error(e.getResponseBodyAsString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateDeliveryInformation(int id, DeliveryInformation info){
        String requestURL = URL+"/"+id;
        HttpEntity<DeliveryInformation> entity = new HttpEntity<>(info, restTemplate.headForHeaders(requestURL));
        try{
            ResponseEntity<DeliveryInformation> response = restTemplate.exchange(requestURL, HttpMethod.PUT, entity, DeliveryInformation.class);
            return response;
        }catch(HttpStatusCodeException e){
            log.error(e.getMessage());
            log.error(e.getResponseBodyAsString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
