package devART.uca.capas.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {
 
    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       //encoder.co
        return encoder.encode(password);
    }
 
    public static void main(String[] args) {
        String password = "1234";
        String encrytedPassword = encrytePassword(password);
 
        //System.out.println("Encryted Password: " + encrytedPassword);
    }
     
}