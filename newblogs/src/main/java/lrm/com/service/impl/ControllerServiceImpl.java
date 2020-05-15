package lrm.com.service.impl;

import lrm.com.entity.Comment;
import lrm.com.mapper.ICommentMapper;
import lrm.com.service.IControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControllerServiceImpl implements IControllerService {
    @Autowired
    private ICommentMapper commentMapper;
    @Override
    public void addComment(Comment comment) {
        try {
            commentMapper.addComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCb(Integer bid, Integer cid) {
        commentMapper.addCb(bid,cid);
    }
}
