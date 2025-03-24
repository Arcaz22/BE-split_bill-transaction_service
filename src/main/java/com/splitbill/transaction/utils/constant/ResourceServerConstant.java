package com.splitbill.transaction.utils.constant;

public class ResourceServerConstant {
    public static final String[] HTTP_HEADER_ALLOWED = {
            "X-Requested-With",
            "Content-Type",
            "Authorization",
            "App-Source",
            "Origin",
            "Accept",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers"
    };

    public static final String[] HTTP_METHOD_ALLOWED = {
            "OPTIONS", "HEAD", "GET", "POST", "DELETE", "PATCH", "PUT", "DELETE", "TRACE"
    };

    public static final String[] HTTP_ORIGIN_ALLOWED = {
            "http://localhost:3000",
            "http://10.14.152.117:8081"
    };

}
