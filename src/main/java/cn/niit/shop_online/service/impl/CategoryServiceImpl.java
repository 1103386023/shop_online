package cn.niit.shop_online.service.impl;

import cn.niit.shop_online.convert.GoodsConvert;
import cn.niit.shop_online.entity.Category;
import cn.niit.shop_online.entity.Goods;
import cn.niit.shop_online.enums.CategoryRecommendEnum;
import cn.niit.shop_online.mapper.CategoryMapper;
import cn.niit.shop_online.mapper.GoodsMapper;
import cn.niit.shop_online.vo.CategoryChildrenGoodsVO;
import cn.niit.shop_online.vo.CategoryVO;
import cn.niit.shop_online.vo.RecommendGoodsVO;
import cn.niit.shop_online.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 1103386023
 * @since 2023-11-07
 */

@Service
@AllArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    private final GoodsMapper goodsMapper;
    @Override
    public List<Category> getIndexCategoryList() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
//        查询首页和分类页都推荐的分类以及在首页推荐的分类
        wrapper.eq(Category::getIsRecommend, CategoryRecommendEnum.ALL_RECOMMEND.getValue()).or().eq(Category::getIsRecommend, CategoryRecommendEnum.INDEX_RECOMMEND.getValue());
        wrapper.orderByDesc(Category::getCreateTime);
        List<Category> list = baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    public List<CategoryVO> getCategoryList() {
        List<CategoryVO> list = new ArrayList<>();
//        1、查询配置在分类tab页上的 父级分类
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getIsRecommend, CategoryRecommendEnum.ALL_RECOMMEND.getValue()).or().eq(Category::getIsRecommend, CategoryRecommendEnum.CATEGORY_HOME_RECOMMEND.getValue());
        List<Category> categories = baseMapper.selectList(wrapper);
//        2、查询该分类下的自己分类
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        for (Category category : categories) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setId(category.getId());
            categoryVO.setName(category.getName());
            categoryVO.setIcon(category.getIcon());
            wrapper.clear();
            wrapper.eq(Category::getParentId, category.getId());
            List<Category> childCategories = baseMapper.selectList(wrapper);
            List<CategoryChildrenGoodsVO> categoryChildrenGoodsList = new ArrayList<>();
//            3、分类下的商品列表
            for (Category item : childCategories) {
                CategoryChildrenGoodsVO childrenGoodsVO = new CategoryChildrenGoodsVO();
                childrenGoodsVO.setId(item.getId());
                childrenGoodsVO.setName(item.getName());
                childrenGoodsVO.setIcon(item.getIcon());
                childrenGoodsVO.setParentId(category.getId());
                childrenGoodsVO.setParentName(category.getName());
                queryWrapper.clear();
                List<Goods> goodsList = goodsMapper.selectList(queryWrapper.eq(Goods::getCategoryId, item.getId()));
                List<RecommendGoodsVO> goodsVOList = GoodsConvert.INSTANCE.convertToRecommendGoodsVOList(goodsList);
                childrenGoodsVO.setGoods(goodsVOList);
                categoryChildrenGoodsList.add(childrenGoodsVO);

            }
            categoryVO.setChildren(categoryChildrenGoodsList);
            list.add(categoryVO);
        }

        return list;
    }
}
