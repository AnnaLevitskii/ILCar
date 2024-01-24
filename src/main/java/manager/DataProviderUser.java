package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Stream;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> userData_negativeReg(){
        List<Object[]> list = new ArrayList<>();
        userData_negativePasswordEmail().forEachRemaining(list::add);
        userData_negativeNameLName().forEachRemaining(list::add);
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> userData_successReg(){
        Random random = new Random();
        int i = random.nextInt(100000000);
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{ new User().setEmail("parker"+i+"@gmail.com").setPassword("Swon634!").withFirstName("Lisa").withLastName("Brown")});
        list.add(new Object[]{ new User().setEmail("john"+i+"@gmail.com").setPassword("John634!").withFirstName("John").withLastName("Doe")});
        list.add(new Object[]{ new User().setEmail("jane"+i+"@gmail.com").setPassword("Jane634!").withFirstName("Jane").withLastName("Doe")});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> userData_negativePasswordEmail(){
        Random random = new Random();
        int i = random.nextInt(100000000);
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{ new User().setEmail("parker"+i+"@gmail.com").setPassword("swon634").withFirstName("Lina").withLastName("Brown")});
        list.add(new Object[]{ new User().setEmail("parker"+i+"@gmail.com").setPassword("").withFirstName("Lina").withLastName("Brown")});
        list.add(new Object[]{ new User().setEmail("").setPassword("Swon634!").withFirstName("Lina").withLastName("Brown")});
        list.add(new Object[]{ new User().setEmail("parker"+i+"gmail.com").setPassword("Swon634!").withFirstName("Lina").withLastName("Brown")});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> userData_negativeNameLName(){
        Random random = new Random();
        int i = random.nextInt(100000000);
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{ new User().setEmail("parker"+i+"@gmail.com").setPassword("Swon634!").withFirstName("").withLastName("Brown")});
        list.add(new Object[]{ new User().setEmail("parker"+i+"@gmail.com").setPassword("Swon634!").withFirstName("Loo").withLastName("")});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> userData_UserExists(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{ new User().setEmail("parker47@gmail.com").setPassword("Swon634!").withFirstName("Lina").withLastName("Nowen")});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> userData_UserExists_fromFile(){
        List<Object[]> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
            String line = reader.readLine();
            while (line!=null) {
                String[] arr = line.split(",");
                list.add(new Object[]{new User().setEmail(arr[0]).setPassword(arr[1]).withFirstName("Lina").withLastName("Nowen")});
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list.iterator();
    }
}
