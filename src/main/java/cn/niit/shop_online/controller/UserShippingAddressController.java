package cn.niit.shop_online.controller;

import cn.niit.shop_online.common.exception.ServerException;
import cn.niit.shop_online.common.result.Result;
import cn.niit.shop_online.service.UserShippingAddressService;
import cn.niit.shop_online.vo.AddressVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.niit.shop_online.common.utils.ObtainUserIdUtils.getUserId;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 1103386023
 * @since 2023-11-07
 */
@Tag(name = "地址管理")
@RestController
@RequestMapping("member")
@AllArgsConstructor
public class UserShippingAddressController {
    private final UserShippingAddressService userShippingAddressService;

    @Operation(summary = "添加收货地址")
    @PostMapping("address")
    public Result<Integer> saveAddress(@RequestBody @Validated AddressVO addressVO, HttpServletRequest request) {
        Integer userId = getUserId(request);
        addressVO.setUserId(userId);
        Integer addressId = userShippingAddressService.saveshippingAddress(addressVO);
        return Result.ok(addressId);
    }
    @Operation(summary = "修改收货地址")
    @PutMapping("address")
    public Result<Integer> editAddress(@RequestBody @Validated AddressVO addressVO, HttpServletRequest request) {
        if (addressVO.getId() == null) {
            throw new ServerException("请求参数不能为空");
        }
        addressVO.setUserId(getUserId(request));
        Integer addressId = userShippingAddressService.editshippingAddress(addressVO);
        return Result.ok(addressId);
    }
    @Operation(summary = "收货地址列表")
    @GetMapping("address")
    public Result<List<AddressVO>> getAddressList( HttpServletRequest request) {
        Integer userId = getUserId(request);
        List<AddressVO> addressList = userShippingAddressService.shippingAddressList(userId);
        return Result.ok(addressList);
    }

    @Operation(summary = "收货地址详情")
    @GetMapping("address/detail")
    public Result<AddressVO> getAddress(Integer id) {
        AddressVO address = userShippingAddressService.shippingAddressDetail(id);
        return Result.ok(address);
    }

    @Operation(summary = "删除收货地址")
    @DeleteMapping("address")
    public Result deleteAddress(Integer id) {
       userShippingAddressService.deleteshippingAddress(id);
       return Result.ok();
    }


}

