package com.ssafy.joblog.domain.user.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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
        return UserResponseDto.fromEntity(user, getFile(userId));
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

    public void deleteFile(int userId) {
        amazonS3Client.deleteObject(bucket, String.valueOf(userId));
    }

    public void uploadFile(MultipartFile file, int userId) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = String.valueOf(userId);
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, fileObj));
        fileObj.delete();
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

    public String getFile(int userId) {
        String urltext = "";
        if(amazonS3Client.doesObjectExist(bucket, String.valueOf(userId))){
            URL url = amazonS3Client.getUrl(bucket, String.valueOf(userId));
            urltext += url;
        }
        return urltext;
    }

    public void changeRole(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다"));
        user.changeRole();
    }
}
