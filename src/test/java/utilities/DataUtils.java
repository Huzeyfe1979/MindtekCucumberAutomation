package utilities;

import java.util.Random;

public class DataUtils {

    /**
    This method will generate random emails.
    Ex:
     .getRandomEmail(); -> returns "ijhfdrr@gmail.com"
     */

    public static String getRandomEmail (){

        // abc8767652gmail.com
        String email="abc";
        Random random = new Random();
        int number = random.nextInt(25000);

        return email+number+"@gmail.com";
    }
}
