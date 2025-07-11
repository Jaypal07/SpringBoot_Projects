package com.jaypal.jobappbackend.controller;

import com.jaypal.jobappbackend.model.JobPost;
import com.jaypal.jobappbackend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping("/jobPosts")
    public List<JobPost> getAllJobs(){
        return  service.getAllJobs();
    }

    @GetMapping("/jobPost/{postId}")
    public JobPost getJobById(@PathVariable("postId") Integer postId){
        return service.getJobById(postId);
    }

    @PostMapping("/jobPost")
    public JobPost addJobPost(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
        return service.getJobById(jobPost.getPostId());
    }

    @PutMapping("/jobPost/{postId}")
    public JobPost updateJobPost(@RequestBody JobPost jobPost){
         service.updateJobPost(jobPost);
         return service.getJobById(jobPost.getPostId());
    }

    @DeleteMapping("/jobPost/{postId}")
    public String deleteJobPost(@PathVariable("postId") Integer postId){
        service.deleteJobPost(postId);
        return "Deleted";
    }
    @GetMapping("/keyword/{search}")
    public List<JobPost> searchJobs(@PathVariable String search){
        return service.searchJobs(search);
    }
}
