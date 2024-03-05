package com.seb.ibanchecker.iban;

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
import com.seb.ibanchecker.iban.models.IbanProcessingException;
import com.seb.ibanchecker.iban.models.IbanRequestBody;
import com.seb.ibanchecker.iban.models.IbanResult;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ValidateEndpointTest {
    @InjectMocks
    private IbanController ibanController;

    @Mock
    private IbanService ibanService;

    @Test
    public void testSuccess() {
        IbanRequestBody requestBody = new IbanRequestBody();
        requestBody.set(Arrays.asList("ibanStr1", "ibanStr2"));

        List<IbanEntity> mockIbanEntities = Arrays.asList(
                new IbanEntity(),
                new IbanEntity()
        );
        
        when(ibanService.processIbans(anyList())).thenReturn(mockIbanEntities);

        ResponseEntity<IbanResult> response = ibanController.validate(requestBody);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().getMsg().length());
        assertEquals(2, response.getBody().getIbansValidation().size());
        assertEquals(0, response.getBody().getIbansDetails().size());
    }

    @Test
    public void testProcessingException() {
        IbanRequestBody requestBody = new IbanRequestBody();
        requestBody.set(Arrays.asList("ibanStr1", "ibanStr2"));
        
        String msg = "processing internal error";
        when(ibanService.processIbans(anyList())).thenThrow(new IbanProcessingException(msg));

        ResponseEntity<IbanResult> response = ibanController.validate(requestBody);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(msg, response.getBody().getMsg());
        assertEquals(0, response.getBody().getIbansValidation().size());
        assertEquals(0, response.getBody().getIbansDetails().size());
    }
}
