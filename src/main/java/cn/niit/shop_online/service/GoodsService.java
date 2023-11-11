package cn.niit.shop_online.service;


import cn.niit.shop_online.common.result.PageResult;
import cn.niit.shop_online.entity.Goods;
import cn.niit.shop_online.query.*;
import cn.niit.shop_online.vo.GoodsVO;
import cn.niit.shop_online.vo.IndexTabRecommendVO;
import cn.niit.shop_online.vo.RecommendGoodsVO;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunyu
 * @since 2023-11-08
 */
public interface GoodsService extends IService<Goods> {
    /**
     * 首页热门推荐 - 根据 tab Id 获取该推荐下的商品列表
     *
     * @return
     */
    IndexTabRecommendVO getTabRecommendGoodsByTabId(RecommendByTabGoodsQuery query);


    /**
     * 首页推荐 - 猜你喜欢(分页)
     *
     * @param query
     * @return
     */
    PageResult<RecommendGoodsVO> getRecommendGoodsByPage(Query query);

    /**
     * 根据id获取商品信息
     *
     * @param id
     * @return
     */
    GoodsVO getGoodsDetail(Integer id);
}
