package lrm.com.service;

import lrm.com.entity.Comment;

public interface IControllerService {
    void addComment(Comment comment);
    void addCb(Integer bid,Integer cid);
}
