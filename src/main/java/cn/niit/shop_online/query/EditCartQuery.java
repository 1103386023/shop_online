package cn.niit.shop_online.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditCartQuery {
    @Schema(description = "主键id")
    @NotNull(message = "主键id不能为空")
    private String id;
    @Schema(description = "是否选中")
    private Boolean selected;
    @Schema(description = "选购商品数量")
    private Integer count;
}

