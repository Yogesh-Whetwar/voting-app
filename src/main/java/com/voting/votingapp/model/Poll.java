package com.voting.votingapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @ElementCollection  //with the help of this annotation we dont need to create a new entity
    private List<String> options = new ArrayList<>();
    //in db a new table will be created with id as pk and option as its other field without even creating a new
    //entity   table name will be poll_options

    @ElementCollection
    private  List<Long> votes = new ArrayList<>();
    //this table name would be poll_votes
}
