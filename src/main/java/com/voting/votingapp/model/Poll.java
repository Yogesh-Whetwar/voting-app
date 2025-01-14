package com.voting.votingapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @ElementCollection  //with the help of this annotation we dont need to create a new entity
    private List<OptionVote> options = new ArrayList<>();
    //in db a new table will be created with id as pk and option as its other field without even creating a new
    //entity   table name will be poll_options

//    @ElementCollection
//    private  List<Long> votes = new ArrayList<>();
//    //this table name would be poll_votes


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<OptionVote> getOptions() {
        return options;
    }

    public void setOptions(List<OptionVote> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", options=" + options +
                '}';
    }
}
