package com.seb.ibanchecker.iban;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seb.ibanchecker.iban.entities.IbanBaseDTO;
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

    @PostMapping(value = "/validate", consumes = "application/json")
    public ResponseEntity<IbanResult> validate(@RequestBody IbanRequestBody requestBody) {
        return processIbans(requestBody, IbanDTOFactory::createValidationDTOs);
    }

    @PostMapping(value = "/recognize", consumes = "application/json")
    public ResponseEntity<IbanResult> recognize(@RequestBody IbanRequestBody requestBody) {
        return processIbans(requestBody, IbanDTOFactory::createDetailsDTOs);
    }

    private ResponseEntity<IbanResult> processIbans(IbanRequestBody requestBody, Function<List<IbanEntity>, List<IbanBaseDTO>> dtoCreator) {
        List<IbanEntity> ibans;

        try {
            ibans = service.processIbans(requestBody.getData());
        } catch (Exception e) {
            ApplicationLogger.error("service internal error: ", e);

            return ResponseEntity.internalServerError().body(
                new ResultBuilder()
                    .msg(e.getMessage())
                    .build()
            );
        }

        return ResponseEntity.ok().body(
            new ResultBuilder()
                .ibanData(dtoCreator.apply(ibans))
                .build()
        );
    }

}
