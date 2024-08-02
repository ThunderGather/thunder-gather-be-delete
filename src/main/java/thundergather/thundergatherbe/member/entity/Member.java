package thundergather.thundergatherbe.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thundergather.thundergatherbe.global.entity.BaseEntity;
import thundergather.thundergatherbe.meeting.entity.Meeting;
import thundergather.thundergatherbe.member.entity.type.MemberRoleType;
import thundergather.thundergatherbe.post.entity.Post;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @Column(nullable = false, unique = true)
      private String email;

      @Column(nullable = false)
      private String password;

      @Column(nullable = false)
      private String nickname;

      private String profileImageUrl;

      @Column(nullable = false)
      private MemberRoleType roleType;

      @Builder.Default
      @OneToMany(mappedBy = "member")
      private List<Post> posts = new ArrayList<>();

      @Builder.Default
      @OneToMany(mappedBy = "member")
      private List<Meeting> myMeetingList = new ArrayList<>();

      public void addPost(Post post) {
            this.posts.add(post);
            post.assignMember(this);
      }

      public void removePost(Post post) {
            this.posts.remove(post);
            post.assignMember(null);
      }

      public void addMeetingMemberList(Meeting meetingMember) {
            this.myMeetingList.add(meetingMember);
            meetingMember.assignToMember(this);
      }

      public void removeMeetingMemberList(Meeting meetingMember) {
            this.myMeetingList.remove(meetingMember);
            meetingMember.assignToMember(null);
      }

}
