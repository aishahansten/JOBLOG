package com.ssafy.joblog.domain.user.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ssafy.joblog.domain.user.dto.request.UserUpdateRequestDto;
import com.ssafy.joblog.domain.user.dto.response.UserResponseDto;
import com.ssafy.joblog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final AmazonS3Client amazonS3Client;

    @PostMapping("/profile/{userId}")
    public ResponseEntity<Void> uploadFile(
            @PathVariable(value = "userId") int userId,
            @RequestParam(value = "file") MultipartFile file) {
        userService.deleteFile(userId);
        userService.uploadFile(file, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/profile/{userId}")
    public String getFile(@PathVariable(value = "userId") int userId) {
        return userService.getFile(userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable(value = "userId") int userId) {
        return ResponseEntity.ok(userService.findUser(userId));
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        userService.update(userUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "userId") int userId) {
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //시연용 삭제
    @DeleteMapping("/permanent/delete/{userId}")
    public ResponseEntity<Void> tempDelete(@PathVariable(value = "userId") int userId) {
        userService.tempDelete(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
