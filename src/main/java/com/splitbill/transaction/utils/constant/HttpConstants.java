package com.splitbill.transaction.utils.constant;

import java.util.concurrent.TimeUnit;

public class HttpConstants {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_APP_SOURCE = "App-Source";
    public static final String HEADER_APP_DATA = "App-Data";

    public static final String HEADER_SIGNATURE_APP_ID = "appid";
    public static final String HEADER_SIGNATURE_TOKEN = "token";
    public static final String HEADER_SIGNATURE_TIMESTAMP = "timestamp";

    public static final Long SIGNATURE_TIME_TOLERANCE = TimeUnit.MINUTES.toSeconds(5);

}
