package com.kbe.application.models.storageAPI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private String country;
    private String city;
    private String street;
    private String homeNr;
    private String postalCode;
}
