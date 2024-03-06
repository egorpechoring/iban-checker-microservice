package com.seb.ibanchecker.iban;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.seb.ibanchecker.iban.entities.IbanEntity;
import com.seb.ibanchecker.iban.entities.IbanProcessingException;
import com.seb.ibanchecker.iban.entities.IbanRequestBody;
import com.seb.ibanchecker.iban.entities.IbanResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class IbanControllerTest {
    @InjectMocks
    private IbanController ibanController;

    @Mock
    private IbanService ibanService;

    @Test
    public void testRecognizeSuccess() {
        IbanRequestBody requestBody = new IbanRequestBody();
        requestBody.setData(Arrays.asList("ibanStr1", "ibanStr2"));

        List<IbanEntity> mockIbanEntities = Arrays.asList(
                new IbanEntity("", false, ""),
                new IbanEntity("", false, "")
        );
        
        when(ibanService.processIbans(anyList())).thenReturn(mockIbanEntities);

        ResponseEntity<IbanResult> response = ibanController.recognize(requestBody);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody().getMsg());
        assertEquals(mockIbanEntities.size(), response.getBody().getData().size());
    }
    @Test
    public void testRecognizeProcessingException() {
        IbanRequestBody requestBody = new IbanRequestBody();
        requestBody.setData(Arrays.asList("ibanStr1", "ibanStr2"));
        
        String msg = "processing internal error";
        when(ibanService.processIbans(anyList())).thenThrow(new IbanProcessingException(msg));

        ResponseEntity<IbanResult> response = ibanController.recognize(requestBody);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(msg, response.getBody().getMsg());
        assertEquals(null, response.getBody().getData());
    }
    @Test
    public void testValidateSuccess() {
        IbanRequestBody requestBody = new IbanRequestBody();
        requestBody.setData(Arrays.asList("ibanStr1", "ibanStr2"));

        List<IbanEntity> mockIbanEntities = Arrays.asList(
            new IbanEntity("", false, ""),
            new IbanEntity("", false, "")
        );
        
        when(ibanService.processIbans(anyList())).thenReturn(mockIbanEntities);

        ResponseEntity<IbanResult> response = ibanController.validate(requestBody);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody().getMsg());
        assertEquals(mockIbanEntities.size(), response.getBody().getData().size());
    }

    @Test
    public void testValidateProcessingException() {
        IbanRequestBody requestBody = new IbanRequestBody();
        requestBody.setData(Arrays.asList("ibanStr1", "ibanStr2"));
        
        String msg = "processing internal error";
        when(ibanService.processIbans(anyList())).thenThrow(new IbanProcessingException(msg));

        ResponseEntity<IbanResult> response = ibanController.validate(requestBody);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(msg, response.getBody().getMsg());
        assertEquals(null, response.getBody().getData());
    }
}
