package com.main.producer.model;

import lombok.Data;


import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Email2 implements Serializable{ 
	
   @Email(message = "Email 2 format is Invalid")
   private String email_address;
   private String category;

}