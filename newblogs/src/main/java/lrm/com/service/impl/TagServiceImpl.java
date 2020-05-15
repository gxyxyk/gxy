package lrm.com.service.impl;

import com.github.pagehelper.PageHelper;
import lrm.com.entity.Tag;
import lrm.com.mapper.ITagMapper;
import lrm.com.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * type业务逻辑层
 */
@Service
public class TagServiceImpl implements ITagService {
    @Autowired
    private ITagMapper tagMapper;

    /**
     * 添加一个type类型
     * @param name
     * @return
     */
    @Override
    public Integer addtag(String name) {
        try {
            return tagMapper.addtag(name);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 查询全部类型并且分页
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Tag> listTag(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return tagMapper.listTags();
    }

    @Override
    public Tag Onetag(Integer id) {
        return tagMapper.Onetag(id);
    }

    @Override
    public Integer deleteTag(Integer id) {
        return tagMapper.deleteTag(id);
    }

    @Override
    public Integer updateTag(String name,Integer id) {
        try {
            return tagMapper.updateTag(id,name);
        } catch (Exception e) {
            return 0;
        }
    }


    @Override
    public List<Tag> list() {
        return tagMapper.listTags();
    }

    @Override
    public void addTag_Blog(Integer bid, Integer tid) {
        tagMapper.addTag_Blog(bid,tid);
    }

    @Override
    public void deleteOnetag(Integer id) {
        tagMapper.deleteOnetag(id);
    }
}
