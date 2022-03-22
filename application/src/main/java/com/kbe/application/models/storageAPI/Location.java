package com.kbe.application.models.storageAPI;

import lombok.*;

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

    @Override
    public final boolean equals(Object o){

        if(o == this)
            return true;
        if(!(o instanceof Location))
            return false;
        Location location = (Location) o;

        boolean countryEquals = (this.country == null && location.country == null)
                || (this.country != null && this.country.equals(location.country));
        boolean cityEquals = (this.city == null && location.city == null)
                || (this.city != null && this.city.equals(location.city));
        boolean streetEquals = (this.street == null && location.street == null)
                || (this.street != null && this.street.equals(location.street));
        boolean homeNrEquals = (this.homeNr == null && location.homeNr == null)
                || (this.homeNr != null && this.homeNr.equals(location.homeNr));
        boolean postalCodeEquals = (this.postalCode == null && location.postalCode == null)
                || (this.postalCode != null && this.postalCode.equals(location.postalCode));

        return countryEquals && cityEquals && streetEquals && homeNrEquals && postalCodeEquals;
    }

    @Override
    public final int hashCode(){
        int result = 17;
        if(country != null){
            result = 31 * result + country.hashCode();
        }
        if(city != null){
            result = 31 * result + city.hashCode();
        }
        if(street != null){
            result = 31 * result + street.hashCode();
        }
        if(homeNr != null){
            result = 31 * result + homeNr.hashCode();
        }
        if(postalCode != null){
            result = 31 * result + postalCode.hashCode();
        }

        return result;
    }

    @Override
    public String toString(){

        return "{\n\t\"country\":\" "+this.country+"\",\n" +
                "\t\"city\":\" "+this.city+"\",\n" +
                "\t\"street\":\" "+this.street+"\",\n"+
                "\t\"homeNr\":\" "+this.homeNr+"\",\n"+
                "\t\"postalCode\":\" "+this.postalCode+"\"\n"+
                "}\n";
    }
}
