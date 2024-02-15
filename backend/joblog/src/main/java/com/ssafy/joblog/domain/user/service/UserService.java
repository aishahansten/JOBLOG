package com.ssafy.joblog.domain.user.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.ssafy.joblog.domain.user.dto.request.UserUpdateRequestDto;
import com.ssafy.joblog.domain.user.dto.response.UserResponseDto;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public UserResponseDto findUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다"));
        return UserResponseDto.fromEntity(user);
    }

    public void update(UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(userUpdateRequestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다"));
        user.updateUser(userUpdateRequestDto);
    }

    public void delete(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다"));
        userRepository.markDeletedUser(userId);
    }

    public void tempDelete(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다"));
        userRepository.delete(user);
    }

    public void deleteFile(String originalName) {
        amazonS3Client.deleteObject(bucket, originalName);
    }

    public void uploadFile(MultipartFile file, int userId) {
        File fileObj = convertMultiPartFileToFile(file);
        String originName = file.getOriginalFilename();
        String changedName = changedImageName(originName);
        amazonS3Client.putObject(new PutObjectRequest(bucket, changedName, fileObj));
        fileObj.delete();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        String originalName = user.getProfileImageName();
        deleteFile(originalName); //기존 파일 존재시 삭제

        String newUrl = amazonS3Client.getUrl(bucket, changedName).toString();
        user.updateFile(changedName, newUrl); //새로운 이미지 등록
    }

    private String changedImageName(String originName) { //이미지 이름 중복 방지를 위해 랜덤으로 생성
        String random = UUID.randomUUID().toString();
        return random + originName;
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    public void changeRole(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다"));
        user.changeRole();
    }
}
