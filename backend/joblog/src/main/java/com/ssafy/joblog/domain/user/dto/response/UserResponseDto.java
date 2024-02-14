package com.ssafy.joblog.domain.user.dto.response;

import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private int id;
    private int companyCode;
    private String email;
    private String nickname;
    private int objective;
    private String realName;
    private String englishName;
    private String address;
    private String userEmail;
    private String profileImageName;
    private String profileImageUrl;
    private String phoneNumber;
    private LocalDate birthDate;
    private String role;
    private String amazonS3FileUrl;

    public static UserResponseDto fromEntity(User user, String amazonS3FileUrl){
        return UserResponseDto.builder()
                .id(user.getId())
                .companyCode(user.getCompanyCode())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .objective(user.getObjective())
                .realName(user.getRealName())
                .englishName(user.getEnglishName())
                .address(user.getAddress())
                .userEmail(user.getUserEmail())
                .profileImageName(user.getProfileImageName())
                .profileImageUrl(user.getProfileImageUrl())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .role(user.getRole().name())
                .amazonS3FileUrl(amazonS3FileUrl)
                .build();
    }
}
