package com.ssafy.joblog.JsonDataReadWrite.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = LAZY )
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    private String location;
    private int locationCode;

    @Builder
    public Location(Recruit recruit, String location, int locationCode) {
        this.recruit = recruit;
        this.location = location;
        this.locationCode = locationCode;
    }
}
