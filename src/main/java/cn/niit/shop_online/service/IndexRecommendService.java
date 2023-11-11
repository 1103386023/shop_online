package cn.niit.shop_online.service;

import cn.niit.shop_online.entity.IndexRecommend;
import cn.niit.shop_online.vo.IndexRecommendVO;
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

public interface IndexRecommendService extends IService<IndexRecommend> {
    List<IndexRecommendVO> getList();
}
