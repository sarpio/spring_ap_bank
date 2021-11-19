package com.example.demo.rest;

import com.example.demo.entity.Type;
import com.example.demo.rest.dto.OperationDTO;
import com.example.demo.services.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/operation/")
public class OperationController {

    private final OperationService operationService;

    @PostMapping
    public ResponseEntity<OperationDTO> createNewOperation(@Valid @RequestBody OperationDTO dto,
                                                           @Valid @RequestParam Type type) {
    return ResponseEntity.ok(operationService.addNewOperation(dto, type));
    }

    @GetMapping()
    public ResponseEntity<List<OperationDTO>> getAccountOperations(@Valid @RequestParam Long id) {
        return ResponseEntity.ok(operationService.getListOfTransactionByAccountId(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteOperationById(@Valid @RequestParam Long id) {
        return ResponseEntity.ok(operationService.cancelOperationById(id));
    }

}
