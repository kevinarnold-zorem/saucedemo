package com.zorem.ct.webbase.utils;

import com.zorem.ct.webbase.utils.configloader.ReadProperties;

import java.net.URI;
import java.net.URISyntaxException;

public class TOTPGenerator {

    public static String getCodeForUser(){
        URI uri = null;
        com.bastiaanjansen.otp.TOTPGenerator totpGenerator = null;
        try {
            uri = new URI("otpauth://totp/issuer?secret="+ ReadProperties.getProperty("user.secret")+"&algorithm=SHA1&digits=6&period=30");
            totpGenerator = com.bastiaanjansen.otp.TOTPGenerator.fromURI(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return totpGenerator.now();
    }
}
