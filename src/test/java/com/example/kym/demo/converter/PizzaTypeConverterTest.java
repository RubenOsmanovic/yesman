package com.example.kym.demo.converter;

import com.example.kym.demo.converters.PizzaTypeConverter;
import com.example.kym.demo.test_2.PizzaType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PizzaTypeConverterTest {

    private PizzaTypeConverter pt = new PizzaTypeConverter();

    @Test
    public void ensureCOnvertToDBworks() {

        PizzaType ptt = PizzaType.XL;

        var converted = pt.convertToDatabaseColumn(ptt);

        assertThat(converted).isNotNull().isSameAs("XL");

    }

    @Test
    public void ensureCOnvertToEntityAttribute() {

        String ptt = "XL";

        var converted = pt.convertToEntityAttribute(ptt);

        assertThat(converted).isNotNull().isSameAs(PizzaType.XL);

    }

}
