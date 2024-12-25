package in.coder.abhijit.demo.util;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SecretKeyGenerator {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Generate a SecretKey for HS512 algorithm
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
        keyGenerator.init(512);  // Specify the key size (in bits)
        SecretKey secretKey = keyGenerator.generateKey();

        // Encode it to Base64 so you can store it in properties or environment
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        System.out.println("Secret Key (Base64 Encoded): " + encodedKey);
    }
}
