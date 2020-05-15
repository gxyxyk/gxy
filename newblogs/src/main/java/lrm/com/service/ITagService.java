package lrm.com.service;


import lrm.com.entity.Tag;

import java.util.List;

public interface ITagService {
    Integer addtag(String name);
    List<Tag> listTag(Integer page, Integer size);
    Tag Onetag(Integer id);
    Integer deleteTag(Integer id);
    Integer updateTag(String name,Integer id);
    List<Tag> list();
    void addTag_Blog(Integer bid,Integer tid);
    void deleteOnetag(Integer id);
}
