/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.dao;

import com.andrievskaja.business.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Людмила
 */
@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Long>, JpaSpecificationExecutor<Contact>{
    
}
