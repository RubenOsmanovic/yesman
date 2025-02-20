package com.example.kym.demo.converter;

import com.example.kym.demo.converters.BuddyTypeConverter;
import com.example.kym.demo.hotel_manager.BuddyType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BuddyTypeConverterTest {

    @Test
    public void ensureConverterWorksToDB() {
        BuddyType bt = BuddyType.REGULAR;
        BuddyTypeConverter bc = new BuddyTypeConverter();
        var newly = bc.convertToDatabaseColumn(bt);
        assertThat(newly).isEqualTo("REGULAR");
    }

    @Test
    public void ensureConverterWorksToEntityType() {
        String bt = "REGULAR";
        BuddyTypeConverter bc = new BuddyTypeConverter();
        var newly = bc.convertToEntityAttribute(bt);
        assertThat(newly).isEqualTo(BuddyType.REGULAR);
    }
}
