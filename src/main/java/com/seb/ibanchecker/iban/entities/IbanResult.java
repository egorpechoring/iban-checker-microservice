package com.seb.ibanchecker.iban.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IbanResult {
    private final String msg;
    private final List<IbanBaseDTO> ibans;

    IbanResult(String msg, List<IbanBaseDTO> ibans){
        this.msg = msg;
        this.ibans = ibans;
    }

    public String getMsg() {
        return this.msg;
    }

    public List<IbanBaseDTO> getData(){
        return this.ibans;
    }

    public static class ResultBuilder {
        private String msg;
        private List<IbanBaseDTO> ibans;

        public ResultBuilder msg(String msg){
            this.msg = msg;
            return this;
        }

        public ResultBuilder ibanData(List<IbanBaseDTO> ibans){
            this.ibans = ibans;
            return this;
        }

        public IbanResult build(){
            return new IbanResult(this.msg, this.ibans);
        }
    } 
}
