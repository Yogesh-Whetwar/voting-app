package com.voting.votingapp.service;

import com.voting.votingapp.model.OptionVote;
import com.voting.votingapp.model.Poll;
import com.voting.votingapp.repository.PollRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PollService {

    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }


    @Transactional
    public Poll createPoll(Poll poll) {
        System.out.println("Saving Poll: " + poll.getQuestion());
        Poll savedPoll = pollRepository.save(poll);
        System.out.println("Saved Poll: " + savedPoll.getQuestion());
        return savedPoll;
    }

    public ResponseEntity<List<Poll>> getAllPolls() {
        List<Poll> polls= pollRepository.findAll();
        if(polls.isEmpty())
        {
            return new ResponseEntity<>(polls,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(polls,HttpStatus.OK);
    }

    public ResponseEntity<Poll> getSinglePoll(Long id) {
        Optional<Poll> poll=pollRepository.findById(id);
        if(poll.isPresent()){
            Poll result=poll.get();
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    public void vote(Long pollId, int optionIndex) {
        //we will get  poll from db
        Poll poll=pollRepository.findById(pollId).orElseThrow(()->new RuntimeException("Poll not found"));
        //get all options or option list
        List<OptionVote> options = poll.getOptions();
        //if index for vote is not valid, throw err
        if(optionIndex <0 || optionIndex>= options.size()){
            throw new IllegalArgumentException("Invalid option index");
        }
        //get selected option
        OptionVote selectedOption = options.get(optionIndex);
        //increment vote for selected option
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);
        //save incremented vote option into the dbs
        pollRepository.save(poll);
    }
}
