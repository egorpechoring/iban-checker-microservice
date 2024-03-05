package com.seb.ibanchecker.iban.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.seb.ibanchecker.util.ApplicationLogger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IbanResult {
    private final String msg;
    private final List<IbanValidationDTO> ibansValidation;

    IbanResult(String msg, List<IbanValidationDTO> ibansValidation){
        this.msg = msg;
        //
        this.ibansValidation = ibansValidation;
    }

    public String getMsg(){
        return this.msg;
    }

    public List<IbanValidationDTO> getIbansValidation(){
        return this.ibansValidation;
    }

    //TODO: complete getIbansDetails & in builder
    public List<IbanValidationDTO> getIbansDetails(){
        return this.ibansValidation;
    }

    public static class ResultBuilder {
        private String msg;
        private List<IbanValidationDTO> ibansValidation;

        public ResultBuilder msg(String msg){
            this.msg = msg;
            return this;
        }

        // builder for ibans
        public ResultBuilder ibanValidations(List<IbanValidationDTO> ibans){
            ApplicationLogger.info("builder saves DTOs");
            this.ibansValidation = ibans;
            return this;
        }

        public IbanResult build(){
            ApplicationLogger.info("builder builds DTOs");
            return new IbanResult(this.msg, ibansValidation);
        }
    } 
}
