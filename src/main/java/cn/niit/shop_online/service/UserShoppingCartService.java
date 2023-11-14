package cn.niit.shop_online.service;

import cn.niit.shop_online.entity.UserShoppingCart;
import cn.niit.shop_online.query.CartQuery;
import cn.niit.shop_online.query.EditCartQuery;
import cn.niit.shop_online.vo.CartGoodsVO;
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
public interface UserShoppingCartService extends IService<UserShoppingCart> {
CartGoodsVO addShopCart(CartQuery query);
List<CartGoodsVO> shopCartList(Integer userId);
 CartGoodsVO editCart(EditCartQuery query);
 void removeCartGoods(Integer userId,List<Integer>ids);
 void editCartSelected(Boolean selected,Integer userId);
}
