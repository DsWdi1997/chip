package com.example.chip.entity;

import lombok.Data;

@Data
public class Chip {
    private int id ;
    private Integer model ;
    private String OE ;

    public Chip(){
        super();
    }

    public Chip(int id){
        this.id =id ;
    }

    public Chip(int id ,Integer model,String OE){
        this.id = id ;
        this.model = model ;
        this.OE = OE ;
    }

    public int getId(){
        return id ;
    }

    public void setId(int id){
        this.id = id ;
    }

    public Integer getModel(){
        return model ;
    }

    public void setModel(Integer model){
        this.model = model ;
    }

    public void setOE(String OE){
        this.OE = OE ;
    }

    public String getOE() {
        return OE;
    }




}
