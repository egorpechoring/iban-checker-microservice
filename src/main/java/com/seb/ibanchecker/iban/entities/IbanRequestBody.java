package com.seb.ibanchecker.iban.entities;

import java.util.List;

public class IbanRequestBody {
    private List<String> data;

    public IbanRequestBody(){}

    public void setData(List<String> data){
        this.data = data;
    }

    public List<String> getData(){
        return this.data;
    }
}
