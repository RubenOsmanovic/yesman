package com.example.kym.demo.converters;

import com.example.kym.demo.hotel_manager.BuddyType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BuddyTypeConverter implements AttributeConverter<BuddyType, String> {


    @Override
    public String convertToDatabaseColumn(BuddyType buddyType) {
        if(buddyType != null){
           switch (buddyType) {
               case REGULAR, ASSISTANT_DIRECTOR, INSTRUCTOR -> {
                   return buddyType.toString();
               }
           }
        }
        else{
            return null;
        }
        return null;
    }

    @Override
    public BuddyType convertToEntityAttribute(String string) {
        if(string != null) {
            switch (string) {
                case "REGULAR":
                    return BuddyType.REGULAR;
                case "INSTRUCTOR":
                    return BuddyType.INSTRUCTOR;
                case "ASSISTANT_DIRECTOR":
                    return BuddyType.ASSISTANT_DIRECTOR;
            }
        }
        else {
            return null;
        }
        return null;
    }
}
