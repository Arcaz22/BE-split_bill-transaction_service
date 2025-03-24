package com.splitbill.transaction.factory.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private Alert alert;
    private T data;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Alert {
        private String requestId;
        private Long requestTimeUnix;
        private Integer code;
        private String message;
        private String ex;
    }

    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .alert(Alert.builder()
                        .requestId(UUID.randomUUID().toString())
                        .requestTimeUnix(Instant.now().getEpochSecond())
                        .code(200)
                        .message("Success")
                        .build())
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> created(T data) {
        return BaseResponse.<T>builder()
                .alert(Alert.builder()
                        .requestId(UUID.randomUUID().toString())
                        .requestTimeUnix(Instant.now().getEpochSecond())
                        .code(201)
                        .message("Resource created successfully")
                        .build())
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> error(int code, String message, String ex) {
        return BaseResponse.<T>builder()
                .alert(Alert.builder()
                        .requestId(UUID.randomUUID().toString())
                        .requestTimeUnix(Instant.now().getEpochSecond())
                        .code(code)
                        .message(message)
                        .ex(ex)
                        .build())
                .build();
    }
}
