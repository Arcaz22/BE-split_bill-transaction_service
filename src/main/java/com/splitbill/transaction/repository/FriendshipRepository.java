package com.splitbill.transaction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.splitbill.transaction.factory.entity.Friendship;
import com.splitbill.transaction.factory.enums.FriendshipStatus;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByRecipientIdAndStatus(Long recipientId, FriendshipStatus status);

    List<Friendship> findByRequesterId(Long requesterId);

    Optional<Friendship> findByRequesterIdAndRecipientId(Long requesterId, Long recipientId);

    List<Friendship> findByRequesterIdOrRecipientIdAndStatus(
        Long requesterId,
        Long recipientId,
        FriendshipStatus status
    );
}
