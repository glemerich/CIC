package com.csc340.CIC.mods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csc340.CIC.comment.*;
import java.util.List;

@Service
public class ModsService {
    @Autowired
    private final ModsRepository modsRepository;

    @Autowired
    private final CommentService commentService;

    public ModsService(ModsRepository modsRepository, CommentService commentService) {
        this.modsRepository = modsRepository;
        this.commentService = commentService;
    }


    public List<Report> getReportedComments(){
        return modsRepository.findAll();
    }

    public void deleteByID(long commentID){
        modsRepository.deleteById(commentID);
    }
    // Method to fetch reported comments
    /* 
    public List<String> getCommentText() {
        return modsRepository.findCommentText();
    }
    */
}
