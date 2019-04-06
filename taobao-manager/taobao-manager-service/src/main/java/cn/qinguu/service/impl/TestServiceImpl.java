package cn.qinguu.service.impl;


import cn.qinguu.mapper.TestMapper;
import cn.qinguu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper mapper;
    @Override
    public String queryNow() {
//        注入mapper

        return mapper.queryNow();
    }
}
