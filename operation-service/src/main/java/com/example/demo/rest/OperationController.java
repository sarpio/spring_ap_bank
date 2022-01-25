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
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/api/operation")
public class OperationController {

    private final OperationService operationService;

    @PostMapping
    public ResponseEntity<OperationDTO> createNewOperation(@RequestBody OperationDTO dto) {
    return ResponseEntity.ok(operationService.addNewOperation(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<OperationDTO>> getAccountOperations(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(operationService.getListOfTransactionByAccountId(id));
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOperationById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(operationService.cancelOperationById(id));
    }

}
