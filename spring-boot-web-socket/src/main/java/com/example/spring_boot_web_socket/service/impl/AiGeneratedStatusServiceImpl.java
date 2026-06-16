package com.example.spring_boot_web_socket.service.impl;

import com.example.spring_boot_web_socket.dto.AiGeneratedStatusRequestDto;
import com.example.spring_boot_web_socket.dto.AiGeneratedStatusResponseDto;
import com.example.spring_boot_web_socket.dto.AiStatusUpdateRequestDto;
import com.example.spring_boot_web_socket.model.AiGeneratedStatus;
import com.example.spring_boot_web_socket.repository.AiGeneratedStatusRepository;
import com.example.spring_boot_web_socket.service.AiGeneratedStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing AI-generated status records.
 * <p>
 * Provides functionality to create, retrieve, and update status records.
 * When a status is updated, the updated information is broadcast to all
 * subscribed WebSocket clients in real time.
 * </p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AiGeneratedStatusServiceImpl implements AiGeneratedStatusService {

    /**
     * Repository for performing database operations on status records.
     */
    private final AiGeneratedStatusRepository statusRepository;

    /**
     * Template used to send messages to WebSocket subscribers.
     */
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Creates a new status record.
     *
     * @param requestDto request containing the title and status details
     */
    @Override
    public void upsertStatus(AiGeneratedStatusRequestDto requestDto) {

        AiGeneratedStatus aiGeneratedStatus = new AiGeneratedStatus();
        aiGeneratedStatus.setTitle(requestDto.getTitle());
        aiGeneratedStatus.setStatus(requestDto.getStatus());

        statusRepository.save(aiGeneratedStatus);
    }

    /**
     * Retrieves all available status records.
     *
     * @return list of status response DTOs
     */
    @Override
    public List<AiGeneratedStatusResponseDto> getStatus() {
        return statusRepository.findAll()
                .stream()
                .map(status -> new AiGeneratedStatusResponseDto(
                        status.getId(),
                        status.getTitle(),
                        status.getStatus()
                ))
                .toList();
    }

    /**
     * Updates the status of an existing record and notifies all
     * connected WebSocket subscribers about the change.
     *
     * @param requestDto request containing the record ID and updated status
     */
    @Override
    public void updateStatus(AiStatusUpdateRequestDto requestDto) {

        final AiGeneratedStatus status = statusRepository
                .findById(requestDto.getId())
                .orElse(null);

        if (status != null) {

            status.setStatus(requestDto.getStatus());
            statusRepository.save(status);

            // Broadcast updated status to WebSocket subscribers
            AiGeneratedStatusResponseDto responseDto =
                    new AiGeneratedStatusResponseDto(
                            status.getId(),
                            status.getTitle(),
                            status.getStatus()
                    );

            messagingTemplate.convertAndSend(
                    "/topic/ai-status",
                    responseDto
            );

        } else {
            log.info("No status record found for ID {}", requestDto.getId());
        }
    }
}
