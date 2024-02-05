package com.ssafy.joblog.domain.resume.dto.response;

import com.ssafy.joblog.domain.user.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeWithInfoResponseDto {
    private ResumeResponseDto resumeResponseDto;
    private String realName;
    private String englishName;
    private String address;
    private String userEmail;
    private String profileImageName;
    private String profileImageUrl;
    private String phoneNumber;
    private LocalDate birthDate;
    private List<InfoResponseDto> infoResponseDtos = new ArrayList<>();


    public ResumeWithInfoResponseDto(User user, ResumeResponseDto resumeResponseDto, List<InfoResponseDto> infoResponseDtos) {
        this.resumeResponseDto = resumeResponseDto;
        this.realName = user.getRealName();
        this.englishName = user.getEnglishName();
        this.address = user.getAddress();
        this.userEmail = user.getUserEmail();
        this.profileImageName = user.getProfileImageName();
        this.profileImageUrl = user.getProfileImageUrl();
        this.phoneNumber = user.getPhoneNumber();
        this.birthDate = user.getBirthDate();
        this.infoResponseDtos = infoResponseDtos;
    }
}
