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
import thundergather.thundergatherbe.meeting.entity.MeetingMember;
import thundergather.thundergatherbe.member.entity.Member;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
      private int maxParticipants;

      private String description;

      @Column(nullable = false)
      private String location;

      private String openChatUrl;

      @Column(nullable = false)
      private int currentParticipants;

      @Builder.Default
      @OneToMany(mappedBy = "post")
      private List<MeetingMember> meetingMembers = new ArrayList<>();


      public void assignMember(Member member) {
            this.member = member;
      }

      public void addMeetingMember(MeetingMember meetingMember) {
            this.meetingMembers.add(meetingMember);
            meetingMember.assignToPost(this);
      }

      public void removeMeetingMember(MeetingMember meetingMember) {
            this.meetingMembers.remove(meetingMember);
            meetingMember.assignToPost(null);
      }
}
