package com.nowbio.database.efn.modules.fa.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author nowbio
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("fa_variants")
@ApiModel(value="FaVariants对象", description="")
public class FaVariants implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String type;

    private Integer chr;

    private Integer position;

    private String ref;

    private String alt;

    private String info;

    private String geneSymbol;

    private String interproNumber;

    private String koNumber;

    private String goNumber;

    private String vulturineKenya;

    private String wildSudan;

    private String wildNigeria;

    private String wildKenya;

    private String domesticSudan;

    private String domesticNigeria;

    private String domesticKenya;

    private String domesticItaly;

    private String domesticIran;

    private String domesticHungary;

    private String domesticChina;


}
