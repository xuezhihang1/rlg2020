package com.itdr.controller;

import com.itdr.common.ServerResponse;
import com.itdr.pojo.Category;
import com.itdr.pojo.Product;
import com.itdr.pojo.vo.ProductVo;
import com.itdr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/portal/product/")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 获取商品直接分类
     * @param pid
     * @return
     */
    @RequestMapping("basecategory.do")
    public ServerResponse<Category> baseCategory(Integer pid){
        return productService.baseCategory(pid);
    }

    /**
     * 获取商品详情
     * @param productId
     * @return
     */
    @RequestMapping("datail.do")
    public ServerResponse<Product> datail(Integer productId){
        return productService.datail(productId);
    }

    /**
     * 商品模糊查询
     * @param keyWord
     * @return
     */
    @RequestMapping("list.do")
    public ServerResponse<ProductVo> list(String keyWord,
                                          @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize",required = false,defaultValue = "5")Integer pageSize,
                                          @RequestParam(value = "orderBy",required = false,defaultValue = "")String orderBy){
        return productService.list(keyWord,pageNum,pageSize,orderBy);
    }
}
