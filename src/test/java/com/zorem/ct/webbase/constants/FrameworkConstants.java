package com.zorem.ct.webbase.constants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FrameworkConstants {

    public static final String PROJECT_PATH = System.getProperty("user.dir");

    @Getter
    private static final String configJsonPath = PROJECT_PATH + "/src/test/resources/config/config.json";
    @Getter
    private static final String configJsonPathWeb = PROJECT_PATH + "/src/test/resources/config/configWeb.json";
    @Getter
    private static final String configWord = PROJECT_PATH + "/src/test/resources/Docs/";
    @Getter
    private static final String pathLog = PROJECT_PATH + "/src/test/resources/Logs/";
    @Getter
    private static final long explicitWait = 15;
    @Getter
    private static final String user1 = "User1";
}
