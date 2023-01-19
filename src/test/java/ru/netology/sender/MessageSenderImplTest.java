package ru.netology.sender;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {
    GeoService geoService = mock(GeoServiceImpl.class);
    LocalizationService localizationService = mock(LocalizationServiceImpl.class);
    MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

    @Test
    void sendRu() {
        when(geoService.byIp(any()))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        when(localizationService.locale(any()))
                .thenReturn("Добро пожаловать");
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", "");
        String expected = "Добро пожаловать";
        String actual = messageSender.send(headers);
        assertEquals(expected, actual);
    }
    @Test
    void sendEn() {
        when(geoService.byIp(any()))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        when(localizationService.locale(any()))
                .thenReturn("Welcome");
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", "");
        String expected = "Welcome";
        String actual = messageSender.send(headers);
        assertEquals(expected, actual);
    }
}