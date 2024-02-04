package com.ssafy.joblog.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {
    private int id;
    private String nickname;
    private String objective;
    private String realName;
    private String englishName;
    private String address;
    private String userEmail;
    private String profileImageName;
    private String profileImageUrl;
    private String phoneNumber;
    private LocalDate birthDate;

}
