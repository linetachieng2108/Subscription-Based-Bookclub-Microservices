package com.example.usermicroservice.configurations;

import com.example.usermicroservice.entity.User;
import com.example.usermicroservice.repository.UserRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    //You cannot autowire JobBuilder or StepBuilder directly. Instead, you should use the JobRepository to create a JobBuilder and StepBuilder.
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private UserRepository userRepository;

//    READER BEAN
    @Bean
    public JdbcCursorItemReader<User> reader(DataSource dataSource){
        return new JdbcCursorItemReaderBuilder<User>()
                .name("userItemReader")
                .dataSource(dataSource)
                .sql("SELECT user_id, username, email, password_hash, phone_number FROM users")
                .rowMapper((rs,rowNum) ->{
                    User user = new User();
                    user.setUser_id(rs.getLong("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword_hash(rs.getString("password_hash"));
                    user.setPhone_number(rs.getString("phone_number"));
                    return user;
                })
                .build();
    }

    //    EXCEL JOB BEAN
//    @Bean
//    public Job userJob(JobRepository jobRepository, Step UserExcelStep){
//        return new JobBuilder("userJob", jobRepository)
//                .start(UserExcelStep)
//                .build();
//    }

//    CSV JOB BEAN
    @Bean
    public Job userJob(Step UserCsvStep) {
        return new JobBuilder("userJob", jobRepository)
                .start(UserCsvStep)
                .build();
    }


    //    EXCEL STEP BEAN
//    @Bean
//    public Step UserExcelStep(JobRepository jobRepository, JdbcCursorItemReader<User> reader, PlatformTransactionManager transactionManager,
//                              ItemProcessor<User, User> processor, UserExcelWriter writer) {
//        return new StepBuilder("UserExcelStep", jobRepository)
//                .<User, User>chunk(10, transactionManager)
//                .reader(reader)
//                .processor(processor)
//                .writer(writer)
//                .build();
//    }

//    CSV STEP BEAN
    @Bean
    public Step UserCsvStep(JobRepository jobRepository, JdbcCursorItemReader<User> reader, PlatformTransactionManager transactionManager,
                            ItemProcessor<User, User> processor, UserCsvWriter csvWriter
    ) {
        return new StepBuilder("UserCsvStep", jobRepository)
                .<User, User>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(csvWriter) // Use the CSV writer directly
                .build();
    }

//    //    EXCEL WRITER BEAN
//    @Bean
//    public ItemWriter<User> userItemWriter(){
//        return new UserExcelWriter();
//    }

    //    PROCESSOR BEAN
    @Bean
    public ItemProcessor<User, User> processor() {
        return user -> user; // Pass-through processor (no transformation)
    }

}
