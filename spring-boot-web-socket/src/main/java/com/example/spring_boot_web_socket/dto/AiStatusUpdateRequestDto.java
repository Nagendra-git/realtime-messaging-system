package com.example.spring_boot_web_socket.dto;

import lombok.Data;

@Data
public class AiStatusUpdateRequestDto {

    private Long id;

    private String status;
}
