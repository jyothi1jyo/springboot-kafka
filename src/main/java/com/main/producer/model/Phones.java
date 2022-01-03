package com.main.producer.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Phones implements Serializable{ 

   private String country_code;
   @Pattern(regexp="^?(\\d{3})?[- ]?(\\d{3})[- ]?(\\d{4})$",
		   message="Mobile number is invalid")
   private String number;
   private String category;
   private Boolean mobile;
   
}
