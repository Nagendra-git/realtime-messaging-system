package com.example.spring_boot_web_socket.contoller;

import com.example.spring_boot_web_socket.dto.AiGeneratedStatusRequestDto;
import com.example.spring_boot_web_socket.dto.AiGeneratedStatusResponseDto;
import com.example.spring_boot_web_socket.dto.AiStatusUpdateRequestDto;
import com.example.spring_boot_web_socket.service.AiGeneratedStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing AI-generated status operations.
 * <p>
 * Provides APIs to:
 * <ul>
 *     <li>Create or update a status record.</li>
 *     <li>Retrieve all status records.</li>
 *     <li>Update the status of an existing record.</li>
 * </ul>
 * </p>
 */
@RequiredArgsConstructor
@RestController
public class AiGeneratedStatusController {

    /**
     * Service responsible for AI-generated status operations.
     */
    private final AiGeneratedStatusService statusService;

    /**
     * Creates a new status record or updates an existing one.
     *
     * @param requestDto the request containing status details
     * @return a success message indicating the status was upserted
     */
    @PostMapping("/status")
    public ResponseEntity<String> upsertStatus(
            @RequestBody AiGeneratedStatusRequestDto requestDto) {

        statusService.upsertStatus(requestDto);
        return ResponseEntity.ok("Status upserted successfully");
    }

    /**
     * Retrieves all available status records.
     *
     * @return a list of status responses
     */
    @GetMapping("/status")
    public ResponseEntity<List<AiGeneratedStatusResponseDto>> getStatus() {

        List<AiGeneratedStatusResponseDto> responseDto = statusService.getStatus();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Updates the status of an existing record.
     *
     * @param requestDto the request containing the updated status information
     * @return a success message indicating the status was updated
     */
    @PutMapping("/update-status")
    public ResponseEntity<String> updateStatus(
            @RequestBody AiStatusUpdateRequestDto requestDto) {

        statusService.updateStatus(requestDto);
        return ResponseEntity.ok("Status updated successfully");
    }
}
