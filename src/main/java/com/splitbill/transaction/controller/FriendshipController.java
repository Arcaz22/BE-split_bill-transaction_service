package com.splitbill.transaction.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.splitbill.transaction.factory.dto.friendship.FriendshipCreateDTO;
import com.splitbill.transaction.factory.dto.friendship.FriendshipUpdateDTO;
import com.splitbill.transaction.factory.response.BaseResponse;
import com.splitbill.transaction.service.friendhsip.FriendshipService;
import com.splitbill.transaction.utils.common.CurrentUserId;

@RestController
@RequestMapping("/friendships")
public class FriendshipController {
    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping("/")
    public ResponseEntity<BaseResponse<Map<String, Object>>> create(@RequestBody FriendshipCreateDTO dto) {
        return ResponseEntity.ok(BaseResponse.success(friendshipService.create(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Map<String, Object>>> update(@PathVariable String id,
            @RequestBody FriendshipUpdateDTO dto) {
        dto.setId(Long.parseLong(id));
        return ResponseEntity.ok(BaseResponse.success(friendshipService.update(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Map<String, Object>>> detail(@PathVariable String id) {
        return ResponseEntity.ok(BaseResponse.success(friendshipService.detail(id)));
    }

    @GetMapping("/")
    public ResponseEntity<BaseResponse<Map<String, Object>>> list(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) Boolean activeOnly) {
        return ResponseEntity.ok(BaseResponse.success(
                friendshipService.list(filter, offset, limit, sortBy, direction, activeOnly)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Map<String, Object>>> delete(
            @PathVariable String id,
            @RequestParam(required = false) Boolean isRestore) {
        return ResponseEntity.ok(BaseResponse.success(friendshipService.delete(id, isRestore)));
    }

    @GetMapping("/pending")
    public ResponseEntity<BaseResponse<Map<String, Object>>> getPendingRequests(
            @CurrentUserId String userId) {
        return ResponseEntity.ok(BaseResponse.success(friendshipService.getPendingRequests(userId)));
    }

    @GetMapping("/friends")
    public ResponseEntity<BaseResponse<Map<String, Object>>> getFriendList(
            @CurrentUserId String userId) {
        return ResponseEntity.ok(BaseResponse.success(friendshipService.getFriendList(userId)));
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<BaseResponse<Map<String, Object>>> acceptFriendRequest(
            @PathVariable String id) {
        return ResponseEntity.ok(BaseResponse.success(friendshipService.acceptFriendRequest(id)));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<BaseResponse<Map<String, Object>>> rejectFriendRequest(
            @PathVariable String id) {
        return ResponseEntity.ok(BaseResponse.success(friendshipService.rejectFriendRequest(id)));
    }
}
