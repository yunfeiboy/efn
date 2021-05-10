package com.nowbio.database.efn.modules.fa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nowbio.database.efn.common.api.CommonPage;
import com.nowbio.database.efn.common.api.CommonResult;
import com.nowbio.database.efn.modules.fa.model.FaVariants;
import com.nowbio.database.efn.modules.fa.model.dto.VariantsQueryParam;
import com.nowbio.database.efn.modules.fa.service.FaVariantsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nowbio
 * @since 2021-04-21
 */
@RestController
@Api(tags = "FaVariantsController", description = "突变基因")
@RequestMapping("/fa/faVariants")
public class FaVariantsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaVariantsController.class);

    @Autowired
    private FaVariantsService variantsService;

    @ApiOperation("分页查询突变基因列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<FaVariants>> listBrand(
            VariantsQueryParam variantsQueryParam,
            @RequestParam(value = "pageNum", defaultValue = "1")
            @ApiParam("页码") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10")
            @ApiParam("每页数量") Integer pageSize,
//            @RequestParam(value = "chr", defaultValue = "")
//            @ApiParam("染色体") Integer chr,
            @RequestParam(value = "start", defaultValue = "0")
            @ApiParam("染色体开始位置") Integer start,
            @RequestParam(value = "end", defaultValue = "0")
            @ApiParam("染色体结束位置") Integer end,
            @RequestParam(value = "type", defaultValue = "")
            @ApiParam("Variation type") String type,
            @RequestParam(value = "geneSymbo", defaultValue = "")
            @ApiParam("geneSymbo") String geneSymbo,
            @RequestParam(value = "interproNumber", defaultValue = "")
            @ApiParam("interproNumber") String interproNumber,
            @RequestParam(value = "koNumber", defaultValue = "")
            @ApiParam("koNumber") String koNumber,
            @RequestParam(value = "goNumber", defaultValue = "")
            @ApiParam("goNumber") String goNumber

    ) {

        QueryWrapper<FaVariants> queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("id");
//        queryWrapper.eq("chr",1);
//        variantsQueryParam
//        queryWrapper.eq(variantsQueryParam.getChr());
        queryWrapper.between("position",start,end);
        Page<FaVariants> page = new Page<>(pageNum, pageSize);
        Page<FaVariants> pageResult = variantsService.page(page,queryWrapper);
        return CommonResult.success(CommonPage.restPage(pageResult));
    }

    @ApiOperation("突变基因统计")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> count(){
        return  CommonResult.success(variantsService.mycount());
    }

    @ApiOperation("突变基因统计")
    @RequestMapping(value = "/counts", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> counts(){
        return  CommonResult.success(variantsService.count());
    }

}

