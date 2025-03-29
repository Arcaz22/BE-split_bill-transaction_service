package com.splitbill.transaction.factory.dto.friendship;

import com.splitbill.transaction.factory.enums.FriendshipStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipUpdateDTO {
    private Long id;
    private FriendshipStatus status;
}
