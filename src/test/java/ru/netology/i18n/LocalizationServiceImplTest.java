package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class LocalizationServiceImplTest {

    LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    void locale() {
        List<Country> countries = Arrays.asList(Country.RUSSIA, Country.GERMANY, Country.USA, Country.BRAZIL);
        List<String> expected = Arrays.asList("Добро пожаловать", "Welcome", "Welcome", "Welcome");
        List<String> actual = new ArrayList<>();
        countries.forEach(country -> actual.add(localizationService.locale(country)));
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}