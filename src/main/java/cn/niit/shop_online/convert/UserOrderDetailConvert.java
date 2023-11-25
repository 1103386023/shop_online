package cn.niit.shop_online.convert;

import cn.niit.shop_online.entity.UserOrder;
import cn.niit.shop_online.vo.OrderDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserOrderDetailConvert {


    UserOrderDetailConvert INSTANCE = Mappers.getMapper(UserOrderDetailConvert.class);


    OrderDetailVO convertToOrderDetailVo(UserOrder userOrder);
}

