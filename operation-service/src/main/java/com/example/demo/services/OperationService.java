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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationService {

    public final OperationRepository operationRepository;
    //    public final AccountWebService accountWebService;
    public final AccountFeignClient accountFeignClient;
    //    public final OperationCache operationCache;

    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' hh:mm a");

    public OperationDTO addNewOperation(OperationDTO dto) {
        Double balance = accountFeignClient.getAccountById(dto.getAccountId()).getBalance();
        Double value;

        if (dto.getType().equals(Type.EXPENSE)) {
            value = -Math.abs(dto.getValue());
        } else {
            value = Math.abs(dto.getValue());
        }
        dto.setValue(value);
        if (balance + value < 0 && dto.getType().equals(Type.EXPENSE)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
//        LocalDateTime time = LocalDateTime.now();
        String time = LocalDateTime.now().toString();

        dto.setTransactionDate(time);
        Operation entity = EntityDtoMapper.map(dto);
        operationRepository.save(entity);
//        operationCache.saveOperationInCache(EntityDtoMapper.map(entity));
        recalculateAccountBalance(entity.getAccountId());
        return EntityDtoMapper.map(entity);
    }

    public String cancelOperationById(Long id) {
        Long accountId;
        Operation operation = operationRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Operation with given id not exists"));
        accountId = operation.getAccountId();
        operationRepository.deleteById(id);
//        operationCache.deleteOperationFromCache(id);
        recalculateAccountBalance(accountId);
        return "Operation with id:" + id + " has been withdrawn";
    }

    public List<OperationDTO> getListOfTransactionByAccountId(Long id) {
//        if(operationCache.getOperationByAccountId(id).isEmpty()) {
        List<Operation> accountOperationsList = operationRepository.findByAccountId(id);
        if (accountOperationsList.size() == 0) {
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with given Id not exists or has no operation registered");
            return null;
        }
        return accountOperationsList
                .stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
    }
//        return operationCache.getOperationByAccountId(id);
//    }

    private AccountDTO recalculateAccountBalance(Long id) {
        List<OperationDTO> operations = getListOfTransactionByAccountId(id);
        Double balance = operations.stream().mapToDouble(OperationDTO::getValue).sum();
        AccountDTO account;
        try {
//            account = accountWebService.getAccount(id).block();
            account = accountFeignClient.getAccountById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provided account id not exists");
        }
        if (account != null) {
            account.setBalance(balance);
            /*FeignClient PUT operation*/
//            return accountWebService.putAccount(account).block();
            return accountFeignClient.putAccount(account.getId(), account);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not exists with given id");
        }
    }
}