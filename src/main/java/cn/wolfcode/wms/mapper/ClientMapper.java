package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.qo.QueryObject;

import java.util.List;

public interface ClientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    Client selectByPrimaryKey(Long id);

    List<Client> selectAll();

    int updateByPrimaryKey(Client record);

    int queryForCount(QueryObject qo);

    List<Client> queryForList(QueryObject qo);
}