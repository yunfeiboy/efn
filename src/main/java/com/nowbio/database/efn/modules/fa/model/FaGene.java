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
@TableName("fa_gene")
@ApiModel(value="FaGene对象", description="")
public class FaGene implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String reference;

    private String geneLocus;

    private String chr;

    private Integer start;

    private Integer end;

    private String strand;

    private String length;

    private String description;

    private String sequenceId;

    private String cds;

    private String pep;


}
