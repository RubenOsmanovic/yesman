package com.example.kym.demo.converters;

import com.example.kym.demo.test_2.PizzaType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PizzaTypeConverter implements AttributeConverter<PizzaType, String> {


    @Override
    public String convertToDatabaseColumn(PizzaType pizzaType) {
        if(pizzaType != null) {

            switch (pizzaType){
                case XL:
                    return "XL";
                case LARGE:
                    return "LARGE";
                case SMALl:
                    return "SMALL";
            }

        }
        return null;
    }

    @Override
    public PizzaType convertToEntityAttribute(String string) {

        if(string != null) {
            switch (string) {
                case "XL":
                    return PizzaType.XL;
                case "LARGE":
                    return PizzaType.LARGE;
                case "SMALL":
                    return PizzaType.SMALl;
            }
        }

        return null;
    }
}
