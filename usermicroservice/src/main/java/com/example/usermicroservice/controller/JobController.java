package com.example.usermicroservice.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job userJob;

    @GetMapping("/start")
    public String startJob() throws Exception {
        // Create job parameters
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis());
//                .toJobParameters();

        // Launch the job

        jobLauncher.run(userJob, jobParametersBuilder.toJobParameters());

        return "User Microservice Job started successfully!";
    }

}
