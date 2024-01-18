package manager;

import models.Car;
import models.Search;
import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviderCar {
    @DataProvider
    public Iterator<Object[]> newCarData_success(){
        List<Object[]> list = new ArrayList<>();
        int i = new Random().nextInt(1000)+1000;
        list.add(new Object[]{ Car.builder()
                                .location("Tel Aviv, Israel")
                                .manufacture("Mazda")
                                .model("M3")
                                .year("2022")
                                .fuel("Petrol")
                                .seats(4)
                                .carClass("C")
                                .registrationNumber(String.valueOf(i))
                                .price(50.)
                                .about("Nice car")
                                .build()});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> carData_existit(){
        List<Object[]> list = new ArrayList<>();
        int i = new Random().nextInt(1000)+1000;
        list.add(new Object[]{ Car.builder()
                                .location("Tel Aviv, Israel")
                                .manufacture("Mazda")
                                .model("M3")
                                .year("2022")
                                .fuel("Petrol")
                                .seats(4)
                                .carClass("C")
                                .registrationNumber("12345")
                                .price(50.)
                                .about("Nice car")
                                .build()});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> carData_negative(){
        List<Object[]> list = new ArrayList<>();
        int i = new Random().nextInt(1000)+1000;
        list.add(new Object[]{ Car.builder()
                .location("")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build()});
        list.add(new Object[]{ Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build()});
        list.add(new Object[]{ Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build()});
        list.add(new Object[]{ Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build()});
        list.add(new Object[]{ Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("1992")
                .fuel("")
                .seats(4)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build()});
        list.add(new Object[]{ Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("1992")
                .fuel("Petrol")
                .seats(null)
                .carClass("C")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build()});
        list.add(new Object[]{ Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("1992")
                .fuel("Petrol")
                .seats(4)
                .carClass("")
                .registrationNumber(String.valueOf(i))
                .price(50.)
                .about("Nice car")
                .build()});
        list.add(new Object[]{ Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("1992")
                .fuel("Petrol")
                .seats(4)
                .carClass("c")
                .registrationNumber("")
                .price(50.)
                .about("Nice car")
                .build()});
        list.add(new Object[]{ Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("1992")
                .fuel("Petrol")
                .seats(4)
                .carClass("c")
                .registrationNumber(String.valueOf(i))
                .price(null)
                .about("Nice car")
                .build()});
        list.add(new Object[]{ Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("1992")
                .fuel("Petrol")
                .seats(4)
                .carClass("c")
                .registrationNumber(String.valueOf(i))
                .price(null)
                .about("Nice car")
                .build()});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> newDateData_success(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{ Search.builder()
                .city("Tel Aviv, Israel")
                .dateFrom("11/20/2024")
                .dateTo("1/07/2025")
                .build()});
        list.add(new Object[]{ Search.builder()
                .city("Tel Aviv, Israel")
                .dateFrom("1/20/2024")
                .dateTo("5/24/2024")
                .build()});
        list.add(new Object[]{ Search.builder()
                .city("Tel Aviv, Israel")
                .dateFrom("1/16/2024")
                .dateTo("1/18/2024")
                .build()});

        return list.iterator();
    }
}
