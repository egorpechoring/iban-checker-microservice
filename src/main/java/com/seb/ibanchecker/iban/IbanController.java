package com.seb.ibanchecker.iban;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seb.ibanchecker.iban.entities.IbanDTOFactory;
import com.seb.ibanchecker.iban.entities.IbanEntity;
import com.seb.ibanchecker.iban.entities.IbanProcessingException;
import com.seb.ibanchecker.iban.entities.IbanRequestBody;
import com.seb.ibanchecker.iban.entities.IbanResult;
import com.seb.ibanchecker.iban.entities.IbanResult.ResultBuilder;
import com.seb.ibanchecker.util.ApplicationLogger;

@RestController
@RequestMapping("/api/secure/iban")
public class IbanController {
    private IbanService service = new IbanService();

    @PostMapping(value = "/validate", consumes = "application/json")
    public ResponseEntity<IbanResult> validate(@RequestBody IbanRequestBody requestBody){
        List<IbanEntity> ibans;

        try{
            ibans = service.processIbans(requestBody.getIbans());
        } catch (IbanProcessingException e){
            ApplicationLogger.error("service internal error: ", e);

            return ResponseEntity.internalServerError().body(
                new ResultBuilder()
                    .msg(e.getMessage())
                    .build()
            );
        }

        return ResponseEntity.ok().body(
            new ResultBuilder()
                .ibanValidations(IbanDTOFactory.createValidationDTOs(ibans))
                .build()
        );
    }

    @PostMapping(value = "/recognize", consumes = "application/json")
    public ResponseEntity<IbanResult> recognize(@RequestBody IbanRequestBody requestBody){
        List<IbanEntity> ibans;

        try{
            ibans = service.processIbans(requestBody.getIbans());
        } catch (IbanProcessingException e){
            ApplicationLogger.error("service internal error: ", e);

            return ResponseEntity.internalServerError().body(
                new ResultBuilder()
                    .msg(e.getMessage())
                    .build()
            );
        }

        return ResponseEntity.ok().body(
            new ResultBuilder()
                .ibanDetails(IbanDTOFactory.createDetailsDTOs(ibans))
                .build()
        );
    }
}
