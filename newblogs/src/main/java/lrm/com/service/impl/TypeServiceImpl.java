package lrm.com.service.impl;

import com.github.pagehelper.PageHelper;
import lrm.com.entity.Type;
import lrm.com.mapper.ITypeMapper;
import lrm.com.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * type业务逻辑层
 */
@Service
public class TypeServiceImpl implements ITypeService {
    @Autowired
    private ITypeMapper typeMapper;

    /**
     * 添加一个type类型
     * @param name
     * @return
     */
    @Override
    public Integer addtype(String name) {
        try {
            return typeMapper.addtype(name);
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
    public List<Type> listTypes(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return typeMapper.listTypes();
    }

    @Override
    public Type Onetype(Integer id) {
        return typeMapper.Onetype(id);
    }

    @Override
    public Integer deleteType(Integer id) {
        return typeMapper.deleteType(id);
    }

    @Override
    public Integer updateType(Type type) {
        try {
            return typeMapper.updateType(type);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Type> list() {
        return typeMapper.listTypes();
    }

    @Override
    public void addType_Blog(Integer bid, Integer tid) {
        typeMapper.addType_Blog(bid,tid);
    }

    @Override
    public void deleteOnetype(Integer id) {
        typeMapper.deleteOnetype(id);
    }
}
