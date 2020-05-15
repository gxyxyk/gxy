package lrm.com.service.impl;

import com.github.pagehelper.PageHelper;
import lrm.com.entity.PigeonHole;
import lrm.com.mapper.IPigeonHoleMapper;
import lrm.com.service.IPigeonHoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PigeonHoleServiceImpl implements IPigeonHoleService {
    @Autowired
    private IPigeonHoleMapper pigeonHoleMapper;
    @Override
    public List<PigeonHole> list() {
        List <PigeonHole> pigeonHoles = pigeonHoleMapper.list();
        return pigeonHoles;
    }
}
