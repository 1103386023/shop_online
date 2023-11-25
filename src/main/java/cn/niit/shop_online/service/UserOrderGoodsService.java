package cn.niit.shop_online.service;

import cn.niit.shop_online.entity.UserOrderGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 1103386023
 * @since 2023-11-07
 */
public interface UserOrderGoodsService extends IService<UserOrderGoods> {
void batchUserOrderGoods(List<UserOrderGoods> list);
}
