package com.example.spring_boot_web_socket.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) used to create or update
 * an AI-generated status record.
 * <p>
 * Contains the title of the status item and its current status value.
 * </p>
 */
@Data
public class AiGeneratedStatusRequestDto {

    /**
     * Title or name associated with the status record.
     */
    private String title;

    /**
     * Current status value.
     * Examples: PENDING, IN_PROGRESS, COMPLETED, FAILED.
     */
    private String status;
}
