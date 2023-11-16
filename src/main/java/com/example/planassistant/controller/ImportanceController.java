package com.example.planassistant.controller;

import com.example.planassistant.common.CommonController;
import com.example.planassistant.domain.enumType.Thing;
import com.example.planassistant.dto.ImportanceReqDto;
import com.example.planassistant.dto.ImportanceResDto;
import com.example.planassistant.repository.ImportanceRepository;
import com.example.planassistant.service.ImportanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/importance")
@Tag(name = "Importance", description = "중요도 관련 API")
public class ImportanceController extends CommonController {
    private final ImportanceService importanceService;

    @Operation(description = "중요도 보여주기")
    @GetMapping
    public ResponseEntity getAllImportance(@AuthenticationPrincipal User user){
        log.info("getAllImportance Call");
        var x = importanceService.getAllImportance(user.getUsername());

        return OkReturn(x);
    }
    @Operation(description = "중요도 수정, Request Body 로는 정수형 하나만 들어옴")
    @PutMapping("/{name}")
    public ResponseEntity updateImportance(@AuthenticationPrincipal User user, @PathVariable Thing name, @RequestBody Integer degree){
        log.info("updateImportance call");
        importanceService.updateImportance(user.getUsername(), name, degree);
        return AcceptedReturn("updated");
    }
}