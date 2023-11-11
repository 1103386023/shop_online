package cn.niit.shop_online.service.impl;

import cn.niit.shop_online.convert.IndexRecommendConvert;
import cn.niit.shop_online.entity.IndexRecommend;
import cn.niit.shop_online.mapper.IndexRecommendMapper;
import cn.niit.shop_online.vo.IndexRecommendVO;
import cn.niit.shop_online.service.IndexRecommendService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
public class IndexRecommendServiceImpl extends ServiceImpl<IndexRecommendMapper, IndexRecommend> implements IndexRecommendService {

    @Override
    public List<IndexRecommendVO> getList() {
        LambdaQueryWrapper<IndexRecommend> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(IndexRecommend::getCreateTime);
        List<IndexRecommend> list = baseMapper.selectList(wrapper);
        List<IndexRecommendVO> results = IndexRecommendConvert.INSTANCE.convertToUserVoList(list);
        return results;
    }
}
