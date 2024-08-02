package thundergather.thundergatherbe.member.service;

import java.util.List;
import thundergather.thundergatherbe.post.dto.PostListResponse;
import thundergather.thundergatherbe.post.dto.PostResponse;

public interface MeetingService {

    PostResponse applyMeeting(Long postId, String email);

    List<PostListResponse> myMeetingList(String email);

    void cancelMeeting(Long postId, String email);
}
