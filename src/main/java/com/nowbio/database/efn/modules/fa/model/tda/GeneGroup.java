package com.nowbio.database.efn.modules.fa.model.tda;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author nowbio
 * @since 2021-04-21
 */
@Getter
@Setter
public class GeneGroup  {

    private String name;


    private String value;




}
