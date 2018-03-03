package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.utils.PageResult;
import cn.wolfcode.wms.utils.PermissionUtils;
import cn.wolfcode.wms.utils.RequiredPermisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper mapper;
    @Autowired
    private ApplicationContext ctx;

    @Override
    public int deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Permission record) {
        mapper.insert(record);
        return 0;
    }

    @Override
    public List<Permission> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = mapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Permission> data = mapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, data);
    }

    @Override
    public void reload() {
        List<String> list = mapper.selectAllExpressions();

        //获取所有controller
        Map<String, Object> controllerBeans = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> beans = controllerBeans.values();
        //获取所有贴了RequiredPermisson注解的方法
        for (Object bean : beans) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(RequiredPermisson.class)) {
                    //如果贴了注解,获取权限表达式
                    String expression = PermissionUtils.getExpression(method);
                    if (!list.contains(expression)) {
                        //获取注解传递参数
                        RequiredPermisson requiredPermisson = method.getAnnotation(RequiredPermisson.class);
                        String permissionName = requiredPermisson.value();
                        //如果权限没有重复,就保存权限
                        Permission p = new Permission();
                        p.setName(permissionName);
                        p.setExpression(expression);
                        mapper.insert(p);
                    }
                }
            }
        }
    }
}
