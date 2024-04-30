package com.csc340.CIC.poll;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll,Integer>{
    List<Poll> findAllByRepresentativeId(String representativeId);
}
