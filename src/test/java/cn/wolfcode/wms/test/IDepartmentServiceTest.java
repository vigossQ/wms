package cn.wolfcode.wms.test;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IDepartmentServiceTest {

    @Autowired
    private IDepartmentService service;

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        Department department = service.selectByPrimaryKey(2L);
        System.out.println(department);
    }

    @Test
    public void selectAll() throws Exception {
        List<Department> departments = service.selectAll();
        for (Department dept :
                departments) {
            System.out.println(dept);
        }
    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }

}