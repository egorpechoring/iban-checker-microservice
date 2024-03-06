package com.seb.ibanchecker.iban.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

// @JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IbanResult {
    private final String msg;
    // TODO: make one list for both options
    private final List<IbanValidationDTO> ibansValidation;
    private final List<IbanDetailsDTO> ibansDetails;

    IbanResult(String msg, List<IbanValidationDTO> ibansValidation, List<IbanDetailsDTO> ibansDetails){
        this.msg = msg;
        this.ibansValidation = (ibansValidation != null) ? ibansValidation : null;
        this.ibansDetails = (ibansDetails != null) ? ibansDetails : null;
    }

    public String getMsg() {
        return Objects.toString(this.msg, "");
    }

    public List<IbanValidationDTO> getIbansData(){
        return this.ibansValidation;
    }

    public List<IbanValidationDTO> getIbansValidation(){
        return this.ibansValidation;
    }

    public List<IbanDetailsDTO> getIbansDetails() {
        return this.ibansDetails;
    }

    public static class ResultBuilder {
        private String msg;
        private List<IbanValidationDTO> ibansValidation;
        private List<IbanDetailsDTO> ibansDetails;

        public ResultBuilder msg(String msg){
            this.msg = msg;
            return this;
        }

        public ResultBuilder ibanValidations(List<IbanValidationDTO> ibans){
            this.ibansValidation = ibans;
            return this;
        }

        public ResultBuilder ibanDetails(List<IbanDetailsDTO> ibans){
            this.ibansDetails = ibans;
            return this;
        }
        public IbanResult build(){
            return new IbanResult(this.msg, ibansValidation, ibansDetails);
        }
    } 
}
