package com.voting.votingapp.repository;

import com.voting.votingapp.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface PollRepository extends JpaRepository<Poll,Long> {
}
