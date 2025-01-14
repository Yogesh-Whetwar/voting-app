package com.voting.votingapp.controllers;


import com.voting.votingapp.model.Poll;
import com.voting.votingapp.request.Vote;
import com.voting.votingapp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private final PollService pollService;

    // Constructor for dependency injection
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll){
        return pollService.createPoll(poll);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Poll>> getAllPolls(){
        return pollService.getAllPolls();

    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Poll> getSinglePoll(@PathVariable Long id){
        return pollService.getSinglePoll(id);
    }


    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote){
         pollService.vote(vote.getPollId(), vote.getOptionIndex());
    }
}
