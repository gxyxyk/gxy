package lrm.com.service;

import lrm.com.entity.Type;

import java.util.List;

public interface ITypeService {
    Integer addtype(String name);
    List<Type> listTypes(Integer page, Integer size);
    Type Onetype(Integer id);
    Integer deleteType(Integer id);
    Integer updateType(Type type);
    List<Type> list();
    void addType_Blog(Integer bid,Integer tid);
    void deleteOnetype(Integer id);
}
