package com.main.producer.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Document(collection = "profiles")
public class Profile implements Serializable{
   @Id
   private String id;
   @NotBlank
   private String firstName;
   @NotBlank
   private String lastName;
   @NotBlank(message = "GenderBio Required")
   private String genderBio;
   	
//   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//   @JsonFormat(pattern = "MM-dd-yyyy")
   private LocalDate birthdate;
   
   
   private String preferred_mode_of_contact;
   private Addresses addresses;

   @Valid
   private Phones phones;
   @Valid
   private Email1 email1;
   @Valid
   private Email2 email2;
   
}