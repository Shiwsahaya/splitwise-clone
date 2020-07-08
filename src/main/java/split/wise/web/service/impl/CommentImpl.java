package split.wise.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import split.wise.web.model.Comment;
import split.wise.web.repository.CommentRepository;
import split.wise.web.service.CommentService;

import javax.transaction.Transactional;


@Service
@Transactional
public class CommentImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Override
    public Comment save(Comment comment) {
        return commentRepo.save(comment);
    }
}
