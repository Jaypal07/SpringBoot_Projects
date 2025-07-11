package com.jaypal.jobappbackend.service;

import com.jaypal.jobappbackend.model.JobPost;
import com.jaypal.jobappbackend.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    public JobRepo repo;


    // method to add a jobPost
    public void addJob(JobPost jobPost) {
        repo.save(jobPost);

    }

    //method to return all JobPosts
    public List<JobPost> getAllJobs() {
        return repo.findAll();


    }


    public JobPost getJobById(Integer postId) {
        return repo.findById(postId).orElse(new JobPost());
    }

    public void updateJobPost(JobPost jobPost) {
        repo.save(jobPost);
    }

    public void deleteJobPost(Integer postId) {
        repo.deleteById(postId);
    }

    public List<JobPost> searchJobs(String search) {
        return repo.findByPostProfileContainingOrPostDescContaining(search, search);
    }
}
