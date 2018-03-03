package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.mapper.ClientMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Client record) {
        mapper.insert(record);
        return 0;
    }

    @Override
    public Client selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Client> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Client record) {
        mapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Client> data = mapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }
}
