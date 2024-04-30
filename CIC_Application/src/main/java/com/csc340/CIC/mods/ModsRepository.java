package com.csc340.CIC.mods;
import com.csc340.CIC.comment.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCommentReportTrue();
}
