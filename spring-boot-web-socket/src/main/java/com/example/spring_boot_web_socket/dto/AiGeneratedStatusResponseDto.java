package com.example.spring_boot_web_socket.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) used to return AI-generated
 * status information to clients.
 * <p>
 * Contains the unique identifier, title, and current status
 * of a status record.
 * </p>
 */
@Data
public class AiGeneratedStatusResponseDto {

    /**
     * Unique identifier of the status record.
     */
    private Long id;

    /**
     * Title or name associated with the status record.
     */
    private String title;

    /**
     * Current status value.
     */
    private String status;

    /**
     * Constructs a response DTO with the specified values.
     *
     * @param id the unique identifier of the status record
     * @param title the title of the status record
     * @param status the current status value
     */
    public AiGeneratedStatusResponseDto(Long id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }
}