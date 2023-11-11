package cn.niit.shop_online.service;

import cn.niit.shop_online.entity.User;
import cn.niit.shop_online.query.LoginResultVO;
import cn.niit.shop_online.query.UserLoginQuery;
import cn.niit.shop_online.query.UserVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 1103386023
 * @since 2023-11-07
 */
public interface UserService extends IService<User> {
    LoginResultVO login(UserLoginQuery query);
    User getUserInfo(Integer userId);
    UserVO editUserInfo(UserVO userVO);
}
