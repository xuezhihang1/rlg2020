package com.itdr.mapper;

import com.itdr.pojo.Category;
import com.itdr.pojo.Product;
import com.itdr.pojo.vo.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(String id);

    int updateByPrimaryKey(Product record);

    Product selectByPrimaryKey(Integer productId);

    List<Product> selectByPname(@Param("keyWord") String keyWord);
}