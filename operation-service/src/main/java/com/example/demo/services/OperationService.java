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
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationService {

    public final OperationRepository operationRepository;
    public final AccountWebService accountWebService;

    public OperationDTO addNewOperation(OperationDTO dto, Type type) {
        Double balance = Objects.requireNonNull(accountWebService.getAccount(dto.getAccountId()).block()).getBalance();
        Double value;
        dto.setType(type);
        if (type.equals(Type.EXPENSE)) {
            value = -Math.abs(dto.getValue());
            dto.setValue(value);
        } else {
            value = Math.abs(dto.getValue());
            dto.setValue(value);
        }
        if (balance + value < 0 && dto.getType().equals(Type.EXPENSE)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        LocalDateTime time = LocalDateTime.now();
        dto.setTransactionDate(time);
        Operation entity = EntityDtoMapper.map(dto);
        operationRepository.save(entity);
        recalculateAccountBalance(entity.getAccountId());
        return EntityDtoMapper.map(entity);
    }

    public String cancelOperationById(Long id) {
        Long accountId;
        Operation operation = operationRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        accountId = operation.getAccountId();
        operationRepository.deleteById(id);
        recalculateAccountBalance(accountId);
        return "Operation with id:" + id + " has been withdrawn";
    }

    public List<OperationDTO> getListOfTransactionByAccountId(Long id) {
        List<Operation> accountOperationsList = operationRepository.findByAccountId(id);
        if (accountOperationsList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
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
