package com.example.spring_boot_web_socket.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class AiGeneratedStatusResponseDto {

    private Long id;

    private String title;

    private String status;

    public AiGeneratedStatusResponseDto(Long id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status =status;
    }
}
