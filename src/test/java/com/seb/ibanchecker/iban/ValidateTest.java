package com.seb.ibanchecker.iban;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.seb.ibanchecker.iban.models.IbanEntity;
import com.seb.ibanchecker.iban.models.IbanRequestBody;
import com.seb.ibanchecker.iban.models.IbanResult;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateTest {
    @InjectMocks
    private IbanController ibanController;

    @Mock
    private IbanService ibanService;

    @Test
    public void testValidateTEST() {
        IbanRequestBody requestBody = new IbanRequestBody();
        requestBody.set(Arrays.asList("iban1", "iban2"));

        List<IbanEntity> mockIbanEntities = Arrays.asList(
                new IbanEntity(),
                new IbanEntity()
        );
        
        when(ibanService.processIbans(anyList())).thenReturn(mockIbanEntities);

        ResponseEntity<IbanResult> response = ibanController.validate(requestBody);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("test", response.getBody().getMsg());
        assertEquals(2, response.getBody().getIbansValidation().size());
    }
}



// @ExtendWith(MockitoExtension.class)
// public class ValidateTest {
//     // @Mock
//     // private IbanService ibanService;

//     @InjectMocks
//     private IbanController ibanController;

//     @Test
//     public void testValidateTEST() {
//         // when(ibanService.method(par)).thenReturn(new Result(...));

//         IbanRequestBody requestBody = new IbanRequestBody();
//         requestBody.set(Arrays.asList("iban1", "iban2"));

//         ResponseEntity<IbanResult> response = ibanController.recognize(requestBody);

//         assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//         assertEquals("test", response.getBody().getMsg());
//     }
// }