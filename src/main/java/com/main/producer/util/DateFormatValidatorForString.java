package com.main.producer.util;

 import javax.validation.ConstraintValidator;
 import javax.validation.ConstraintValidatorContext;
 import javax.validation.constraints.Past;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;

 public class DateFormatValidatorForString implements ConstraintValidator<Past, String>{

     @Override
     public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'_'HH:mm:ss");
         df.setLenient(false);
         try {
             df.parse(date);
         } catch (ParseException e) {
             return false;
         }
         return true;
     }


 }