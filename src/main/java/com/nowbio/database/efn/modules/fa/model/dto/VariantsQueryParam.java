package com.nowbio.database.efn.modules.fa.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 分页查询参数
 * @author yun
 * @since 2021/5/10
 */
@Getter
@Setter
public class VariantsQueryParam {

//    @NonNull
    @ApiModelProperty(value = "chr")
    private String chr;

    @ApiModelProperty(value = "Variation type")
    private String type;
}
