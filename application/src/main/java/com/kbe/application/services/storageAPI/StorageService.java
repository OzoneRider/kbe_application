package com.kbe.application.services.storageAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StorageService {

    @Autowired
    private RestTemplate restTemplate;

    private String URL = "http://kbe-storage";
}
