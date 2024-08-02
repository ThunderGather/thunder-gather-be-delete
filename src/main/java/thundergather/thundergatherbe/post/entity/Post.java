package thundergather.thundergatherbe.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thundergather.thundergatherbe.global.entity.BaseEntity;
import thundergather.thundergatherbe.meeting.entity.Meeting;
import thundergather.thundergatherbe.member.entity.Member;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Post extends BaseEntity {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "member_id")
      private Member member;

      @Column(nullable = false)
      private String category;

      @Column(nullable = false)
      private String title;

      @Column(nullable = false)
      private LocalDate desiredDate;

      @Column(nullable = false)
      private LocalTime desiredTime;

      @Column(nullable = false)
      private Integer maxParticipants;

      private String description;

      @Column(nullable = false)
      private String location;

      private String openChatUrl;

      @Builder.Default
      @OneToMany(mappedBy = "post")
      private List<Meeting> meetingMembers = new ArrayList<>();

      public void updatePost(Post updatedPost) {
            this.title = updatedPost.getTitle();
            this.category = updatedPost.getCategory();
            this.desiredDate = updatedPost.getDesiredDate();
            this.desiredTime = updatedPost.getDesiredTime();
            this.maxParticipants = updatedPost.getMaxParticipants();
            this.description = updatedPost.getDescription();
            this.location = updatedPost.getLocation();
            this.openChatUrl = updatedPost.getOpenChatUrl();
      }

      public void assignMember(Member member) {
            this.member = member;
      }

      public void addMeetingMember(Meeting meetingMember) {
            this.meetingMembers.add(meetingMember);
            meetingMember.assignToPost(this);
      }

      public void removeMeetingMember(Meeting meetingMember) {
            this.meetingMembers.remove(meetingMember);
            meetingMember.assignToPost(null);
      }
}
