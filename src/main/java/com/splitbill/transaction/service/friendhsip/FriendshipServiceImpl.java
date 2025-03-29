package com.splitbill.transaction.service.friendhsip;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.splitbill.transaction.factory.dto.friendship.FriendshipCreateDTO;
import com.splitbill.transaction.factory.dto.friendship.FriendshipUpdateDTO;
import com.splitbill.transaction.factory.entity.Friendship;
import com.splitbill.transaction.factory.enums.FriendshipStatus;
import com.splitbill.transaction.factory.exception.ResourceNotFoundException;
import com.splitbill.transaction.factory.mapper.FriendshipMapper;
import com.splitbill.transaction.repository.FriendshipRepository;
import com.splitbill.transaction.service.notification.NotificationService;

@Service
@Transactional
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final FriendshipMapper friendshipMapper;
    private final NotificationService notificationService;

    public FriendshipServiceImpl(
        FriendshipRepository friendshipRepository,
        FriendshipMapper friendshipMapper,
        NotificationService notificationService
    ) {
        this.friendshipRepository = friendshipRepository;
        this.friendshipMapper = friendshipMapper;
        this.notificationService = notificationService;
    }

    @Override
    public Map<String, Object> acceptFriendRequest(String friendshipId) {
        Friendship friendship = friendshipRepository.findById(Long.parseLong(friendshipId))
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found"));

        friendship.setStatus(FriendshipStatus.ACCEPTED);
        Friendship updated = friendshipRepository.save(friendship);

        // Send notification to requester
        notificationService.sendNotification(
            friendship.getRequesterId().toString(),
            "Your friend request was accepted!",
            "FRIENDSHIP_ACCEPTED"
        );

        return Collections.singletonMap("data", friendshipMapper.toDto(updated));
    }

    @Override
    public Map<String, Object> create(FriendshipCreateDTO createDto) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        Friendship friendship = friendshipMapper.createDtoToEntity(createDto);
        friendship.setRequesterId(Long.parseLong(userId));
        friendship.setStatus(FriendshipStatus.PENDING);

        Friendship saved = friendshipRepository.save(friendship);

        // Send notification to recipient
        notificationService.sendNotification(
            createDto.getRecipientId().toString(),
            "You have a new friend request",
            "FRIENDSHIP_REQUEST"
        );

        return Collections.singletonMap("data", friendshipMapper.toDto(saved));
    }

    @Override
    public Map<String, Object> list(String search, long page, int size, String sortBy, String direction, Boolean isActive) {
        // Implementation for listing all friendships
        return Collections.emptyMap();
    }

    @Override
    public Map<String, Object> detail(String id) {
        Friendship friendship = friendshipRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found"));
        return Collections.singletonMap("data", friendshipMapper.toDto(friendship));
    }

    @Override
    public Map<String, Object> update(FriendshipUpdateDTO updateDto) {
        // Implementation for updating friendship
        return Collections.emptyMap();
    }

    @Override
    public Map<String, Object> delete(String id, Boolean isPermament) {
        friendshipRepository.deleteById(Long.parseLong(id));
        return Collections.singletonMap("message", "Friendship deleted successfully");
    }

    @Override
    public Map<String, Object> getFriendList(String userId) {
        List<Friendship> friendships = friendshipRepository.findByRequesterIdOrRecipientIdAndStatus(
            Long.parseLong(userId),
            Long.parseLong(userId),
            FriendshipStatus.ACCEPTED
        );
        return Collections.singletonMap("data", friendshipMapper.toDtos(friendships));
    }

    @Override
    public Map<String, Object> getPendingRequests(String userId) {
        List<Friendship> pendingRequests = friendshipRepository.findByRecipientIdAndStatus(
            Long.parseLong(userId),
            FriendshipStatus.PENDING
        );
        return Collections.singletonMap("data", friendshipMapper.toDtos(pendingRequests));
    }

    @Override
    public Map<String, Object> rejectFriendRequest(String friendshipId) {
        Friendship friendship = friendshipRepository.findById(Long.parseLong(friendshipId))
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found"));

        friendship.setStatus(FriendshipStatus.REJECTED);
        Friendship updated = friendshipRepository.save(friendship);

        // Send notification to requester
        notificationService.sendNotification(
            friendship.getRequesterId().toString(),
            "Your friend request was rejected",
            "FRIENDSHIP_REJECTED"
        );

        return Collections.singletonMap("data", friendshipMapper.toDto(updated));
    }
}
