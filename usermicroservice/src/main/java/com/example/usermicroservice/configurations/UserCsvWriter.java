package com.example.usermicroservice.configurations;

import com.example.usermicroservice.entity.User;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class UserCsvWriter extends FlatFileItemWriter<User> {
    public UserCsvWriter(){
//        CREATING THE OUTPUT FILE
        String fileName = "users_service.csv";
        setResource(new FileSystemResource(fileName));
        System.out.println("CSV file created at:" +new File(fileName));

//        THE CSV FORMAT I WANT(commas as the delimiter)
        DelimitedLineAggregator<User> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");

//        MAP FIELDS FROM THE USERS TABLE TO CSV COLUMNS
        BeanWrapperFieldExtractor<User> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"user_id", "username", "email", "password_hash", "phone_number"});
        lineAggregator.setFieldExtractor(fieldExtractor);
        setLineAggregator(lineAggregator);

    }
}
