package com.kbe.application.models.externalAPI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeoCode {

    @NotNull
    private String lat;
    @NotNull
    private String lon;
}
