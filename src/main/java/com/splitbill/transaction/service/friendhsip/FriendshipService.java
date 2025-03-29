package com.splitbill.transaction.service.friendhsip;

import java.util.Map;
import com.splitbill.transaction.factory.dto.friendship.FriendshipCreateDTO;
import com.splitbill.transaction.factory.dto.friendship.FriendshipUpdateDTO;
import com.splitbill.transaction.factory.service.BaseService;

public interface FriendshipService extends BaseService<FriendshipCreateDTO, FriendshipUpdateDTO> {
    Map<String, Object> getPendingRequests(String userId);

    Map<String, Object> getFriendList(String userId);

    Map<String, Object> acceptFriendRequest(String friendshipId);
    
    Map<String, Object> rejectFriendRequest(String friendshipId);
}
