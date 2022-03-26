package com.kbe.application.models;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Min(0)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @DecimalMin("10.00")
    @DecimalMax("5000.00")
    @Digits(integer = 4, fraction = 2)
    private BigDecimal priceEuro;

    @NotNull
    private String manufacturer;

    @NotNull
    @DecimalMin("10.0")
    @DecimalMax("50.0")
    @Digits(integer = 2, fraction = 1)
    private BigDecimal displaySizeInches;

    @NotNull
    private String color;

    @Min(30)
    @Max(360)
    private int refreshRateHz;

    @NotNull
    @DecimalMin("0.25")
    @DecimalMax("15.0")
    @Digits(integer = 2, fraction = 3)
    private BigDecimal weightKg;

    @Min(1)
    @Max(10)
    private int reactionTimeMs;

    @NotNull
    private String displayInterface;

    @NotNull
    private String resolution;


    @Override
    public final boolean equals(Object o){

        if(o == this)
            return true;
        if(!(o instanceof Product))
            return false;

        Product product = (Product) o;

        boolean nameEquals = (this.name == null && product.name == null)
                || (this.name != null && this.name.equals(product.name));
        boolean priceEquals = (this.priceEuro == null && product.priceEuro == null)
                || (this.priceEuro != null && this.priceEuro.compareTo(product.priceEuro) == 0);
        boolean manufacturerEquals = (this.manufacturer == null && product.manufacturer == null)
                || (this.manufacturer != null && this.manufacturer.equals(product.manufacturer));
        boolean displaySizeEquals = (this.displaySizeInches == null && product.displaySizeInches == null)
                || (this.displaySizeInches != null && this.displaySizeInches.compareTo(product.displaySizeInches) == 0);
        boolean colorEquals = (this.color == null && product.color == null)
                || (this.color != null && this.color.equals(product.color));
        boolean refreshRateEquals = this.refreshRateHz == product.refreshRateHz;
        boolean weightEquals = (this.weightKg == null && product.weightKg == null)
                || (this.weightKg != null && this.weightKg.compareTo(product.weightKg) == 0);
        boolean reactionTimeEquals = this.reactionTimeMs == product.reactionTimeMs;
        boolean displayInterfaceEquals = (this.displayInterface == null && product.displayInterface == null)
                || (this.displayInterface != null && this.displayInterface.equals(product.displayInterface));
        boolean resolutionEquals = (this.resolution == null && product.resolution == null)
                || (this.resolution != null && this.resolution.equals(product.resolution));

        return nameEquals && priceEquals && manufacturerEquals && displaySizeEquals && colorEquals
                && refreshRateEquals && weightEquals && reactionTimeEquals && displayInterfaceEquals && resolutionEquals;
    }

    @Override
    public final int hashCode(){
        int result = 17;
        if(name != null){
            result = 31 * result + name.hashCode();
        }
        if(priceEuro != null){
            result = 31 * result + priceEuro.hashCode();
        }
        if(manufacturer != null){
            result = 31 * result + manufacturer.hashCode();
        }
        if(displaySizeInches != null){
            result = 31 * result + displaySizeInches.hashCode();
        }
        if(color != null){
            result = 31 * result + color.hashCode();
        }
        if(refreshRateHz >= 30){
            result = 31 * result + refreshRateHz;
        }
        if(weightKg != null){
            result = 31 * result + weightKg.hashCode();
        }
        if(reactionTimeMs > 0){
            result = 31 * result + reactionTimeMs;
        }
        if(displayInterface != null){
            result = 31 * result + displayInterface.hashCode();
        }
        if(resolution != null){
            result = 31 * result + resolution.hashCode();
        }
        return result;
    }

    @Override
    public String toString(){

        return "{\n\t\"name\":\" "+this.name+"\",\n" +
                "\t\"priceEuro\":\" "+this.priceEuro.toString()+"\",\n" +
                "\t\"manufacturer\":\" "+this.manufacturer+"\",\n"+
                "\t\"displaySizeInches\":\" "+this.displaySizeInches.toString()+"\",\n"+
                "\t\"color\":\" "+this.color+"\"\n"+
                "\t\"refreshRateHz\":\" "+this.refreshRateHz+"\",\n" +
                "\t\"weightKg\":\" "+this.weightKg.toString()+"\",\n"+
                "\t\"reactionTimeMs\":\" "+this.reactionTimeMs+"\",\n"+
                "\t\"displayInterface\":\" "+this.displayInterface+"\"\n"+
                "\t\"resolution\":\" "+this.resolution+"\"\n"+
                "}\n";
    }
}
