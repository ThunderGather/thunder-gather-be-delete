package thundergather.thundergatherbe.meeting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thundergather.thundergatherbe.global.entity.BaseEntity;
import thundergather.thundergatherbe.member.entity.Member;
import thundergather.thundergatherbe.post.entity.Post;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingMember extends BaseEntity {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "meeting_id")
      private Post post;

      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "member_id")
      private Member member;

      @Column(nullable = false)
      private boolean author;


      public void assignToPost(Post post) {
            this.post = post;
      }

      public void assignToMember(Member member) {
            this.member = member;
      }

      public void addPostAndMember(Post post, Member member) {
            this.post = post;
            this.member = member;

            post.addMeetingMember(this);
            member.addMeetingMemberList(this);
      }
}
