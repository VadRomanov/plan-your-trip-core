package com.planyourtrip.core.util;

import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@UtilityClass
public class TextUtils {

    public String encodeBase64String(String string) {
        var encoder = Base64.getEncoder();
        return encoder.encodeToString(string.getBytes(StandardCharsets.UTF_8));
    }
}
