package com.kbe.application.models.storageAPI;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @NotNull
    @Size(min = 1, max = 50)
    private String country;

    @NotNull
    @Size(min = 1, max = 50)
    private String city;

    @NotNull
    @Size(min = 5, max = 100)
    private String street;

    @NotNull
    @Size(min = 1, max = 6)
    private String homeNr;

    @NotNull
    @Size(min = 1, max = 20)
    private String postalCode;
}
