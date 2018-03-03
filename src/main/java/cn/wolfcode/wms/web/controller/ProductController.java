package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.qo.ProductQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IProductService;
import cn.wolfcode.wms.utils.JsonResult;
import cn.wolfcode.wms.utils.RequiredPermisson;
import cn.wolfcode.wms.utils.UploadUtil;
import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService service;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private ServletContext ctx;

    @RequestMapping("list")
    @RequiredPermisson("商品列表")
    public String list(Model model, @ModelAttribute("qo") ProductQueryObject qo) {
        model.addAttribute("result", service.query(qo));
        model.addAttribute("brands", brandService.selectAll());
        return "product/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiredPermisson("商品删除")
    public JsonResult delete(Long id, String imagePath) {
        JsonResult result = new JsonResult();
        try {
            if (id != null) {
                service.deleteByPrimaryKey(id);
                UploadUtil.deleteFile(ctx, imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("删除错误");
        }
        return result;
    }

    @RequestMapping("input")
    @RequiredPermisson("商品编辑")
    public String input(Long id, Model model) {
        if (id != null) {
            Product product = service.selectByPrimaryKey(id);
            model.addAttribute("product", product);
        }
        model.addAttribute("brands", brandService.selectAll());
        return "product/input";
    }

    @RequestMapping("saveOrUpdate")
    @RequiredPermisson("商品保存或更新")
    @ResponseBody
    public JsonResult saveOrUpdate(Product product, MultipartFile pic) {
        if (pic != null && !StringUtils.isEmpty(product.getImagePath())) {
            UploadUtil.deleteFile(ctx, product.getImagePath());
        }
        if (pic != null) {
            String realPath = ctx.getRealPath("/upload");
            String imagePath = UploadUtil.upload(pic, realPath);
            product.setImagePath(imagePath);
        }
        JsonResult result = new JsonResult();
        try {
            if (product.getId() != null) {
                service.updateByPrimaryKey(product);
            } else {
                service.insert(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark("保存失败");
        }
        return result;
    }

    @RequestMapping("selectProductList")
    public String selectProductList(Model model, @ModelAttribute("qo") ProductQueryObject qo) {
        model.addAttribute("result", service.query(qo));
        model.addAttribute("brands", brandService.selectAll());
        return "product/selectProductList";
    }
}
