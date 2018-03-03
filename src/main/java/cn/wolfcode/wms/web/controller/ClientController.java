package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("client")
public class ClientController {

    @Autowired
    private IClientService service;

    @RequestMapping("list")
    @RequiredPermisson("客户列表")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        model.addAttribute("result", service.query(qo));
        return "client/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermisson("客户删除")
    public JsonResult delete(Long id) {
        JsonResult result = new JsonResult();
        try {
            if (id != null) {
                service.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除错误");
        }
        return result;
    }

    @RequestMapping("input")
    @RequiredPermisson("客户编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            Client client = service.selectByPrimaryKey(id);
            model.addAttribute("client", client);
        }
        return "client/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermisson("客户保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(Client client) {
        JsonResult result = new JsonResult();
        try {
            if (client.getId() != null) {
                service.updateByPrimaryKey(client);
            } else {
                service.insert(client);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.mark("保存失败");
        }
        return result;
    }
}
