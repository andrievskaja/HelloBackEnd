/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service;

import com.andrievskaja.business.service.model.form.RegisteForm;
import com.andrievskaja.business.service.model.view.UserView;

/**
 *
 * @author Людмила
 */
public interface UserService {
    
    public UserView createUSer(RegisteForm registeForm);
    
}
