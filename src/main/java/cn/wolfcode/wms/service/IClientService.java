package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.utils.PageResult;

import java.util.List;

public interface IClientService {
    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    Client selectByPrimaryKey(Long id);

    List<Client> selectAll();

    int updateByPrimaryKey(Client record);

    PageResult query(QueryObject qo);
}
