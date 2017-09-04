package jwt.utils;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class SimpleKeyGenerator implements KeyGenerator {
    @Override
    public Key generateKey() {
        String keyString = "Mohan-Pradeep-BlogSite";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }
}