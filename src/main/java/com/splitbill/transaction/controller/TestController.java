package com.splitbill.transaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitbill.transaction.factory.dto.UserInfoDto;
import com.splitbill.transaction.factory.response.BaseResponse;
import com.splitbill.transaction.utils.common.CurrentUserId;

@RestController
@RequestMapping("/transaction")
public class TestController {

    @GetMapping("/")
    public ResponseEntity<BaseResponse<String>> getSecureData(@CurrentUserId String userId) {
        return ResponseEntity.ok(BaseResponse.success("Secured data for user: " + userId));
    }

    @GetMapping("/current-user")
    public ResponseEntity<BaseResponse<UserInfoDto>> getCurrentUser(@CurrentUserId String userId) {
        // Buat DTO untuk informasi user
        UserInfoDto userInfo = new UserInfoDto();
        userInfo.setUserId(userId);

        // Di sini Anda bisa menambahkan logika untuk mendapatkan data user lainnya
        // misalnya dengan memanggil service atau repository yang sesuai

        return ResponseEntity.ok(BaseResponse.success(userInfo));
    }
}
