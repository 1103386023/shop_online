package cn.niit.shop_online.service.impl;

import cn.niit.shop_online.common.exception.ServerException;
import cn.niit.shop_online.common.result.Result;
import cn.niit.shop_online.convert.AddressConvert;
import cn.niit.shop_online.entity.UserShippingAddress;
import cn.niit.shop_online.enums.AddressDefaultEnum;
import cn.niit.shop_online.enums.AddressDeleteFlagEnum;
import cn.niit.shop_online.mapper.UserShippingAddressMapper;
import cn.niit.shop_online.service.UserShippingAddressService;
import cn.niit.shop_online.vo.AddressVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 1103386023
 * @since 2023-11-07
 */
@Service
public class UserShippingAddressServiceImpl extends ServiceImpl<UserShippingAddressMapper, UserShippingAddress> implements UserShippingAddressService {

    @Override
    public Integer saveshippingAddress(AddressVO addressVO) {
        UserShippingAddress convert = AddressConvert.INSTANCE.convert(addressVO);
        if (addressVO.getIsDefault() == AddressDefaultEnum.DEFAULT_ADDRESS.getValue()) {
            List<UserShippingAddress> list = baseMapper.selectList(new LambdaQueryWrapper<UserShippingAddress>().eq(UserShippingAddress::getIsDefault, AddressDefaultEnum.DEFAULT_ADDRESS.getValue()));
            if (list.size() > 0) {
                throw new ServerException("已经存在默认地址，请勿重复操作");
            }
        }

    save( convert);
    return convert.getId();

}

    @Override
    public Integer editshippingAddress(AddressVO addressVO) {
        UserShippingAddress userShippingAddress=baseMapper.selectById(addressVO.getId());
        if (userShippingAddress==null){
            throw  new ServerException("地址不存在");

        }
        if (addressVO.getIsDefault()== AddressDefaultEnum.DEFAULT_ADDRESS.getValue()){
            UserShippingAddress address=baseMapper.selectOne(new
                    LambdaQueryWrapper<UserShippingAddress>().eq(UserShippingAddress::getUserId,addressVO.getUserId()).eq(UserShippingAddress::getIsDefault,
                    AddressDefaultEnum.DEFAULT_ADDRESS.getValue()));
            if(address!=null){
                address.setIsDefault(AddressDefaultEnum.NOT_DEFAULT_ADDRESS.getValue());
                updateById(address);
            }
        }
        UserShippingAddress address= AddressConvert.INSTANCE.convert(addressVO);
        updateById(address);
        return address.getId();
    }

    @Override
    public AddressVO shippingAddressDetail(Integer id) {
        UserShippingAddress address = baseMapper.selectById(id);
        if(address==null||address.getDeleteFlag()==1){
            throw new ServerException("该地址不存在");
        }
        return AddressConvert.INSTANCE.convertToAddressVO(address);

    }

    @Override
    public List<AddressVO> shippingAddressList(Integer userId) {
        List<UserShippingAddress> list = baseMapper.selectList(new LambdaQueryWrapper<UserShippingAddress>().eq(UserShippingAddress::getUserId, userId).eq(UserShippingAddress::getDeleteFlag,0));
        return AddressConvert.INSTANCE.convertToAddressVOList(list);

    }

    @Override
    public void deleteshippingAddress(Integer id) {
        UserShippingAddress address = baseMapper.selectById(id);
        if(address==null||address.getDeleteFlag()==1){
            throw new ServerException("该地址不存在");
        }
        address.setDeleteFlag(1);
        int i = baseMapper.updateById(address);
        if(i!=1){
            throw new ServerException("删除失败");
        }
    }


}

