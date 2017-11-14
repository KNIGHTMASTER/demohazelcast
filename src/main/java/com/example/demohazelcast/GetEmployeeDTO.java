/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demohazelcast;

import java.io.Serializable;

/**
 *
 * @author fauzi
 */
public class GetEmployeeDTO implements Serializable{
    private static final long serialVersionUID = -4022410224781468193L;
    
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
