package com.jaypal.jobappbackend.model;

import java.util.List;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobPost {

    @Id
    private int postId;
    private String postProfile;
    private String postDesc;
    private Integer reqExperience;
    @ElementCollection
    @CollectionTable(
            name = "post_tech_stack",
            joinColumns = @JoinColumn(name = "post_id")
    )
    @Column(name = "tech")
    private List<String> postTechStack;


}
