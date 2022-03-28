package com.kbe.application.models.storageAPI;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInformation {

    @Min(0)
    private int id;

    @Min(1)
    @Max(100)
    private int deliveryTimeDays;

    @Min(1)
    @Max(9999)
    private int amount;

    @NotNull
    private Location productLocation;

    @Override
    public final boolean equals(Object o){

        if(o == this)
            return true;
        if(!(o instanceof DeliveryInformation))
            return false;
        DeliveryInformation deliveryInformation = (DeliveryInformation) o;

        boolean locationEquals = (this.productLocation == null && deliveryInformation.productLocation == null)
                || (this.productLocation != null && this.productLocation.equals(deliveryInformation.productLocation));
        boolean deliveryTimeEquals = this.deliveryTimeDays == deliveryInformation.deliveryTimeDays;
        boolean amountEquals = this.amount == deliveryInformation.amount;

        return locationEquals && deliveryTimeEquals && amountEquals;
    }

    public final int hashCode(){
        int result = 17;
        if(productLocation != null){
            result = 31 * result + productLocation.hashCode();
        }
        if(deliveryTimeDays > 0){
            result = 31 * result + deliveryTimeDays;
        }
        if(amount >= 0){
            result = 31 * result + amount;
        }

        return result;
    }

    @Override
    public String toString(){

        return "{\n\t\"id\":\""+this.id +"\",\n" +
                "\t\"deliveryTimeDays\":\""+this.deliveryTimeDays+"\",\n" +
                "\t\"amount\":\""+this.amount+"\",\n"+
                "\t\"location\": "+this.productLocation.toString()+
                "}\n";
    }
}
