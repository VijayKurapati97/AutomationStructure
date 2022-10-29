package com.finvisage.utils;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.enums.ConfigProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class PropertyFileReader {

    private PropertyFileReader() {

    }

    private static Properties property = new Properties();
    private static final Map<String, String> CONFIGMAP = new HashMap<>();

    static {
        FileInputStream fs;
        try {
            fs = new FileInputStream(FrameworkConstants.getConfigFilePath());
            property.load(fs);
            for (Map.Entry<Object, Object> entry : property.entrySet()) {
                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String get(ConfigProperties key) throws Exception {
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new Exception("Property name" + key + "is not found check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());

    }


}
