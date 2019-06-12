package pl.kamieniarzola.clinicappws.shared.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class RandomGenerator {


    private static final Random RANDOM = new SecureRandom();


    public String generateRandomId(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i <length ; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
}
