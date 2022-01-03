package com.main.producer.model;


import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Addresses implements Serializable { 

   private String st_address;
   private String city;
   private String state;
   private String zip;
   private String country;

}