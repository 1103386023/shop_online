package cn.niit.shop_online.service.impl;

import cn.niit.shop_online.entity.IndexCarousel;
import cn.niit.shop_online.mapper.IndexCarouselMapper;
import cn.niit.shop_online.service.IndexCarouselService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 200613
 * @since 2023-11-07
 */
@Service
public class IndexCarouselServiceImpl extends ServiceImpl<IndexCarouselMapper, IndexCarousel> implements IndexCarouselService {
    @Override
    public List<IndexCarousel> getList(Integer distributionSite) {
//        使用wrapper 设置查询条件,当distributionSite 不为空时，作为条件查询
//        distributionSite为1时，查询首页广告，为2时为商品分类页广告
        LambdaQueryWrapper<IndexCarousel> wrapper = new LambdaQueryWrapper<>();
        if (distributionSite != null) {
            wrapper.eq(IndexCarousel::getType, distributionSite);
        }
//        设置排序根据创建时间倒叙排序
        wrapper.orderByDesc(IndexCarousel::getCreateTime);
//        查询广告列表
        List<IndexCarousel> list = baseMapper.selectList(wrapper);
        return list;
    }
}
