package cn.niit.shop_online.service;

import cn.niit.shop_online.entity.UserOrder;
import cn.niit.shop_online.vo.OrderDetailVO;
import cn.niit.shop_online.vo.UserOrderVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 1103386023
 * @since 2023-11-07
 */
public interface UserOrderService extends IService<UserOrder> {
Integer addGoodsOrder(UserOrderVO orderVO);
    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    OrderDetailVO getOrderDetail(Integer id);
}
