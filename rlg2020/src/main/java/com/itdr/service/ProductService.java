package com.itdr.service;

import com.itdr.common.ServerResponse;
import com.itdr.pojo.Category;
import com.itdr.pojo.Product;
import com.itdr.pojo.vo.ProductVo;

public interface ProductService {
    ServerResponse<Category> baseCategory(Integer pid);

    ServerResponse<Product> datail(Integer productId);

    ServerResponse<ProductVo> list(String keyWord,Integer pageNum,Integer pageSize,String orderBy);
}
