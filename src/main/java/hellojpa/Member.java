package hellojpa;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        // team 의 members 는 읽기 전용이기 때문에
        // DB 에서 다시 읽기 전까지는 상태가 변하지 않음
        // 그렇기 떄문에 수동으로 List 에 member 를 추가해주는 것이 필요
        team.getMembers().add(this);
    }
}
