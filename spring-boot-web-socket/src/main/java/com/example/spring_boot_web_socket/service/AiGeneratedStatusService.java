package com.example.spring_boot_web_socket.service;

import com.example.spring_boot_web_socket.dto.AiGeneratedStatusRequestDto;
import com.example.spring_boot_web_socket.dto.AiGeneratedStatusResponseDto;
import com.example.spring_boot_web_socket.dto.AiStatusUpdateRequestDto;

import java.util.List;

public interface AiGeneratedStatusService {
    void upsertStatus(AiGeneratedStatusRequestDto requestDto);

    List<AiGeneratedStatusResponseDto> getStatus();

    void updateStatus(AiStatusUpdateRequestDto requestDto);
}
