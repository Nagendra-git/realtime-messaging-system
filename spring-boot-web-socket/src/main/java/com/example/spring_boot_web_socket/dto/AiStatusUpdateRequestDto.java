package com.example.spring_boot_web_socket.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) used to update the status
 * of an existing AI-generated status record.
 * <p>
 * Contains the identifier of the status record and the
 * new status value to be applied.
 * </p>
 */
@Data
public class AiStatusUpdateRequestDto {

    /**
     * Unique identifier of the status record to update.
     */
    private Long id;

    /**
     * New status value for the record.
     */
    private String status;
}
