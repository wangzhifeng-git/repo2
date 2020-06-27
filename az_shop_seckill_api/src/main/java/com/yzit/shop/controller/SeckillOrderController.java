package com.yzit.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzit.framework.web.ui.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.yzit.shop.entity.SeckillOrder;
import com.yzit.shop.service.SeckillOrderService;

@RestController
@RequestMapping("/api/seckillOrder")
@CrossOrigin
@Api("秒杀订单管理")
public class SeckillOrderController {

    @Autowired
    private SeckillOrderService seckillOrderService;

    /**
     * 分页 方法
     * @param seckillOrder
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页方法")
    public PageInfo<SeckillOrder> page(SeckillOrder seckillOrder  ){
        PageHelper.startPage( seckillOrder.getPageNo(),seckillOrder.getPageSize()  );

        List<SeckillOrder> seckillOrderList = seckillOrderService.findByList(seckillOrder);
        PageInfo<SeckillOrder> pageInfo  = new PageInfo<SeckillOrder>(seckillOrderList);
        return pageInfo;
    }

    /**
     * 查询所有的秒杀订单
     * @return
     */
    @ApiOperation("所有秒杀订单")
    @GetMapping("/list")
    public List<SeckillOrder> list(){
       return  seckillOrderService.findAll();
    }

    /**
     * 根据id查询秒杀订单对象
     * @param id
     * @return
     */
    @ApiOperation("根据id查询秒杀订单")
    @GetMapping("/{id}")
    public SeckillOrder findById(@PathVariable Long  id){
       return  seckillOrderService.findById(id);
    }

    /**
     * 添加秒杀订单
     * @param seckillOrder
     * @return
     */
    @ApiOperation("添加秒杀订单")
    @PostMapping("/seckillOrder")
    public AjaxResult save( @RequestBody SeckillOrder seckillOrder   ){
       int count =  seckillOrderService.save(seckillOrder);
       if(count > 0 ){
	       return AjaxResult.OK();
       }
       return AjaxResult.error("新增秒杀订单失败");
    }

    /**
     * 修改秒杀订单
     * @param seckillOrder
     * @return
     */
    @ApiOperation("修改秒杀订单")
    @PutMapping("/seckillOrder")
    public AjaxResult update( @RequestBody  SeckillOrder seckillOrder){
        int count =seckillOrderService.update(seckillOrder);

        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.error("修改秒杀订单失败");
    }
    @ApiOperation("删除秒杀订单")
    @DeleteMapping("/{id}")
    public AjaxResult  del( @PathVariable Long id  ){
        int count = seckillOrderService.del(id);
        if(count > 0 ){
         	return AjaxResult.OK();
        }
        return AjaxResult.error("删除秒杀订单失败");
    }
    @ApiOperation("批量删除秒杀订单")
    @DeleteMapping("/batch/{ids}")
    public AjaxResult  batchDel(@PathVariable Long []  ids  ){
       boolean   success =  seckillOrderService.batchDel(ids);
       if(success){//删除成功
           return AjaxResult.OK();
       }
        return AjaxResult.error("批量删除秒杀订单失败");
    }
}