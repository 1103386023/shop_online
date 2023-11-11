package cn.niit.shop_online.service;

import cn.niit.shop_online.entity.Category;
import cn.niit.shop_online.vo.CategoryVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 1103386023
 * @since 2023-11-07
 */

public interface CategoryService extends IService<Category> {
    List<Category> getIndexCategoryList();
    List<CategoryVO> getCategoryList();

}
