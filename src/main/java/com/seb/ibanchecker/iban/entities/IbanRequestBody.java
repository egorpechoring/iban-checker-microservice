package com.seb.ibanchecker.iban.entities;

import java.util.List;

public class IbanRequestBody {
    private List<String> data;

    public void set(List<String> data){
        this.data = data;
    }

    public List<String> getIbans(){
        return this.data;
    }
}
