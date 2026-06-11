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

@RequiredArgsConstructor
@RestController
public class AiGeneratedStatusController {


    private final AiGeneratedStatusService statusService;

    @PostMapping("/status")
    public ResponseEntity<String> upsertStatus(@RequestBody AiGeneratedStatusRequestDto requestDto){

        statusService.upsertStatus(requestDto);
        return ResponseEntity.ok("Status upserted successfully");
    }

    @GetMapping("/status")
    public ResponseEntity<List<AiGeneratedStatusResponseDto>> getStatus(){
        List<AiGeneratedStatusResponseDto> responseDto = statusService.getStatus();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/update-status")
    public ResponseEntity<String> updateStatus(@RequestBody AiStatusUpdateRequestDto requestDto){
        statusService.updateStatus(requestDto);
        return ResponseEntity.ok("Status updated successfully");
    }
}
