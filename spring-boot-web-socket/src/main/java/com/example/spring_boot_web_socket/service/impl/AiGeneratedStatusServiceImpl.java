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

@Slf4j
@Service
@RequiredArgsConstructor
public class AiGeneratedStatusServiceImpl implements AiGeneratedStatusService {

    private final AiGeneratedStatusRepository statusRepository;

    private final SimpMessagingTemplate messagingTemplate; // ✅ inject this
    @Override
    public void upsertStatus(AiGeneratedStatusRequestDto requestDto) {

        AiGeneratedStatus aiGeneratedStatus = new AiGeneratedStatus();
        aiGeneratedStatus.setTitle(requestDto.getTitle());
        aiGeneratedStatus.setStatus(requestDto.getStatus());
        statusRepository.save(aiGeneratedStatus);
    }

    @Override
    public List<AiGeneratedStatusResponseDto> getStatus() {
        return statusRepository.findAll()
                .stream()
                .map(staus -> new AiGeneratedStatusResponseDto(
                        staus.getId(),staus.getTitle(),staus.getStatus()
                )).toList();
    }

    @Override
    public void updateStatus(AiStatusUpdateRequestDto requestDto) {

        final AiGeneratedStatus status = statusRepository.findById(requestDto.getId()).orElse(null);

        if(status != null){
            status.setStatus(requestDto.getStatus());
            statusRepository.save(status);                         // ✅ save to DB

            // ✅ push updated record to all WebSocket subscribers
            AiGeneratedStatusResponseDto responseDto = new AiGeneratedStatusResponseDto(
                    status.getId(),
                    status.getTitle(),
                    status.getStatus()
            );
            messagingTemplate.convertAndSend("/topic/ai-status", responseDto); // ✅ push

        } else {
            log.info("No Status record found for given Id {}", requestDto.getId());
        }
    }
}
