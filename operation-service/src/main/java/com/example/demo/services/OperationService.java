package com.example.demo.services;

import com.example.demo.entity.Operation;
import com.example.demo.entity.Type;
import com.example.demo.repo.OperationRepository;
import com.example.demo.rest.dto.AccountDTO;
import com.example.demo.rest.dto.OperationDTO;
import com.example.demo.util.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationService {

    public final OperationRepository operationRepository;
    public final AccountWebService accountWebService;

    public OperationDTO addNewOperation(OperationDTO dto, Type type) {
        dto.setType(type);
        dto.setTransactionDate(LocalDateTime.now());
        Operation entity = EntityDtoMapper.map(dto);
        operationRepository.save(entity);
        recalculateAccountBalance(entity.getAccountId());
        return EntityDtoMapper.map(entity);
    }

    public String cancelOperationById(Long id) {
        Operation entity;
        if (operationRepository.existsById(id)) {
            operationRepository.deleteById(id);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "Operation with id:" + id + " has been withdrawn";
    }

    public List<OperationDTO> getListOfTransactionByAccountId(Long id) {
        List<Operation> accountOperationsList = operationRepository.findByAccountId(id);
        return accountOperationsList
                .stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
    }

    private AccountDTO recalculateAccountBalance(Long id) {
        List<OperationDTO> operations = getListOfTransactionByAccountId(id);
        Double balance = operations.stream().mapToDouble(OperationDTO::getValue).sum();
        AccountDTO account;
        try {
            account = accountWebService.getAccount(id).block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (account != null) {
            account.setBalance(balance);
            /*WebClient PUT operation*/
            return accountWebService.putAccount(account).block();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


}
