package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class GeoServiceImplTest {
    GeoService geoService = new GeoServiceImpl();

    @Test
    void byIp() {
        List<String> ips = Arrays.asList(
                "127.0.0.1",
                "172.0.32.11",
                "96.44.183.149",
                "172.0.33.12",
                "96.45.184.150",
                "");
        List<Location> expected = Arrays.asList(
                new Location(null, null, null, 0),
                new Location("Moscow", Country.RUSSIA, "Lenina", 15),
                new Location("New York", Country.USA, " 10th Avenue", 32),
                new Location("Moscow", Country.RUSSIA, null, 0),
                new Location("New York", Country.USA, null,  0),
                null);
        List<Location> actual = new ArrayList<>();
        ips.forEach(s -> actual.add(geoService.byIp(s)));
        assertArrayEquals(expected.toArray(), actual.toArray());

//        Location nullLocation = geoService.byIp(null);  Нет проверки на null в методе
    }
    @Test
    void byCoordinates() {
        Exception exception = assertThrows(RuntimeException.class, () -> geoService.byCoordinates(0.0, 0.0));
        String expectedMsg = "Not implemented";
        String actualMsg = exception.getMessage();
        assertTrue(actualMsg.contains(expectedMsg));
    }
}