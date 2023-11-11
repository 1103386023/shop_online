package cn.niit.shop_online.service;

import cn.niit.shop_online.entity.IndexCarousel;
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

public interface IndexCarouselService extends IService<IndexCarousel> {
    List<IndexCarousel> getList(Integer distributionSite);
}
