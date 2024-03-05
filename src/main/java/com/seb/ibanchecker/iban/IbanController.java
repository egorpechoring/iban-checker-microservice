package com.seb.ibanchecker.iban;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seb.ibanchecker.iban.models.BaseDTO;
import com.seb.ibanchecker.iban.models.IbanEntity;
import com.seb.ibanchecker.iban.models.IbanRequestBody;
import com.seb.ibanchecker.iban.models.IbanResult;
import com.seb.ibanchecker.iban.models.IbanResult.ResultBuilder;
import com.seb.ibanchecker.util.ApplicationLogger;

@RestController
@RequestMapping("/api/secure/iban")
public class IbanController {
    // place for service connection
    private IbanService service = new IbanService();

    @PostMapping(value = "/validate", consumes = "application/json")
    public ResponseEntity<IbanResult> validate(@RequestBody IbanRequestBody requestBody){
        ApplicationLogger.info("requestBody received");

        // TODO: try catch
        List<IbanEntity> ibans = service.processIbans(requestBody.getIbans());
        ApplicationLogger.info("ibans processed");

        return ResponseEntity.internalServerError().body(
            new ResultBuilder()
                .msg("test")
                .ibanValidations(BaseDTO.createValidationDTOs(ibans))
                .build()
        );
    }

    @PostMapping(value = "/recognize", consumes = "application/json")
    public ResponseEntity<IbanResult> recognize(@RequestBody IbanRequestBody requestBody){
        return ResponseEntity.internalServerError().body( new ResultBuilder().msg("test").build());
    }
}
