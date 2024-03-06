package com.seb.ibanchecker.iban;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seb.ibanchecker.iban.entities.IbanDTOFactory;
import com.seb.ibanchecker.iban.entities.IbanEntity;
import com.seb.ibanchecker.iban.entities.IbanRequestBody;
import com.seb.ibanchecker.iban.entities.IbanResult;
import com.seb.ibanchecker.iban.entities.IbanResult.ResultBuilder;
import com.seb.ibanchecker.util.ApplicationLogger;

@RestController
@RequestMapping("/api/secure/iban")
public class IbanController {
    private IbanService service;
    @Autowired
    public IbanController(IbanService service) {
        this.service = service;
    }

    // TODO: REFACTOR ENDPOINTS
    @PostMapping(value = "/validate", consumes = "application/json")
    public ResponseEntity<IbanResult> validate(@RequestBody IbanRequestBody requestBody){
        List<IbanEntity> ibans;
        try{
            ibans = service.processIbans(requestBody.getData());
        } catch (Exception e){
            ApplicationLogger.error("service internal error: ", e);

            return ResponseEntity.internalServerError().body(
                new ResultBuilder()
                    .msg(e.getMessage())
                    .build()
            );
        }

        return ResponseEntity.ok().body(
            new ResultBuilder()
                .ibanData(IbanDTOFactory.createValidationDTOs(ibans))
                .build()
        );
    }

    @PostMapping(value = "/recognize", consumes = "application/json")
    public ResponseEntity<IbanResult> recognize(@RequestBody IbanRequestBody requestBody){
        List<IbanEntity> ibans;

        try{
            ibans = service.processIbans(requestBody.getData());
        } catch (Exception e){
            ApplicationLogger.error("service internal error: ", e);

            return ResponseEntity.internalServerError().body(
                new ResultBuilder()
                    .msg(e.getMessage())
                    .build()
            );
        }

        return ResponseEntity.ok().body(
            new ResultBuilder()
                .ibanData(IbanDTOFactory.createDetailsDTOs(ibans))
                .build()
        );
    }
}
