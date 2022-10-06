package com.example.mapper;

import com.example.movieapi.MovieapiApplication;
import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.service.dto.UserRateDto;
import com.example.movieapi.service.mapper.UserRateMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
@SpringBootTest(classes = MovieapiApplication.class)
class UserRateMapperTest {

    @Autowired
    private UserRateMapper mapper;

    @Test
    void givenEntityToDto_whenMaps_thenCorrect() {
        UserRate entity= new UserRate();
        entity.setTitle("Black Swan");
        entity.setId(new UserMovieId("tt1375666","mahdi"));
        UserRateDto dto = mapper.toDto(entity);

        assertEquals(entity.getId().getUsername(), dto.getUsername());
        assertEquals(entity.getId().getImdbId(), dto.getImdbId());
        assertEquals(entity.getTitle(), dto.getTitle());
    }
    @Test
    void givenDtoToEntity_whenMaps_thenCorrect() {
        UserRateDto dto = new UserRateDto();
        dto.setTitle("Black Swan");
        dto.setUsername("mahdi");
        dto.setImdbId("tt1375666");
        UserRate entity = mapper.toEntity(dto);

        assertEquals(dto.getUsername(), entity.getId().getUsername());
        assertEquals(dto.getImdbId(), entity.getId().getImdbId());
        assertEquals(dto.getTitle(), entity.getTitle());
    }

//    @Test
//    void  givenDtoToEntity_whenMaps_thenCorrect_allDependency(){
//        Account entityAccount;
//        AccountTransaction accountTransaction1;
//        Set<AccountTransaction> accountTransactionList=new HashSet<>();
//
//        entityAccount = new Account(1L,accountTransactionList,null);
//        entityAccount.setId(1L);
//        entityAccount.setCreatedAt(new Date());
//
//        accountTransaction1 = new AccountTransaction(1,10,1L,entityAccount);
//        accountTransaction1.setId(1L);
//
//        accountTransactionList.add(accountTransaction1);
//
//        AccountDto accountDto = mapper.toDto(entityAccount);
//
//        assertEquals(entityAccount.getId(), accountDto.getId());
//        assertEquals(entityAccount.getBalance(), accountDto.getBalance());
//        assertEquals(entityAccount.getAccountTransactions().size(), accountDto.getAccountTransactions().size());
//        assertEquals(1, accountDto.getAccountTransactions().size());
//
//        AccountTransactionDto accountTransactionDto= accountDto.getAccountTransactions().stream().findFirst().get();
//        assertEquals(accountTransaction1.getId(), accountTransactionDto.getId());
//        assertEquals(accountTransaction1.getReferenceNo(), accountTransactionDto.getReferenceNo());
//        assertEquals(accountTransaction1.getNewBalance(), accountTransactionDto.getNewBalance());
//        assertEquals(accountTransaction1.getAmount(), accountTransactionDto.getAmount());
//    }
}
