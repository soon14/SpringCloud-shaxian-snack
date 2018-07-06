package com.isxxc.web;


import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.JsonXMLUtils;
import com.isxxc.domain.dto.OrderRefundDTO;
import com.isxxc.domain.dto.OrderRefundListDTO;
import com.isxxc.domain.dto.Pager;

import com.isxxc.service.feign.store.OrderRefundService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 订单退款 前端控制器
 * </p>
 *
 * @author mc
 * @since 2018-05-09
 */
@RestController
@RequestMapping("/orderRefund")
public class OrderRefundController {

    @Resource
    private OrderRefundService orderRefundService;

    /***
     * 后台管理系统查询申请退款列表订单信息
     * @param pager
     * @return
     */
    @RequestMapping(value = "listrefundPage", method = RequestMethod.POST)
    public Result listRefundPage(@RequestBody Pager pager) {
        return orderRefundService.listRefundPage(pager);
    }

    /***
     * 后台管理系统审核商家审核通过退款订单（提供批量审核）
     * @param orderRefundListDTO
     * @return
     */
    @RequestMapping(value = "backstageAuditRefund", method = RequestMethod.POST)
    public Result backstageAuditRefund(@RequestBody OrderRefundListDTO orderRefundListDTO) {
        if (orderRefundListDTO == null) {
            return ResponseResult.paramNotNull("退款订单信息不能为空");
        }
        if (orderRefundListDTO.getListorderRefundDTO().size() < 1) {
            return ResponseResult.paramNotNull("没有传入订单信息");
        }
        return orderRefundService.backstageAuditRefund(orderRefundListDTO);
    }

    /***
     * 根据订单信息进行退款
     * @param id
     * @return
     */
    @RequestMapping(value = "payRefund", method = RequestMethod.POST)
    public Result payRefund(@RequestParam("id") Integer id,@RequestParam("userId") Integer userId,@RequestParam("returnPerson") String returnPerson) {

        //开始校验（主要校验后台是否能够退款，并返回原因）


        //判断是单个单据支付，还是整个多个单据支付

        //如果是单个单据支付，首先检查是部分退款还是全部退款，新增一个实体属性，由前端传入，判断是全退，不退邮费，还是自定义退款，自定义退款金额需要做限制，不能大于还能退款金额
        //退款之后，需要做两个地方修改，一个是修改订单，将订单信息改为已经退款，不让再次退款。一个是商家金额表的数据修改和记录，将退款金额减掉


        //如果是多个单据支付，也分三种情况退款，和单个订单退款的区别在于，修改订单的时候和商家金额的时候需要根据order_pay_merger表得到支付时提交的合并订单号
        //其他操作全部需要，具体到每个订单信息，需要做遍历

        Pager pager = new Pager();
        pager.putParam("id",id);
        pager.putParam("userId",userId);
        pager.putParam("returnPerson",returnPerson);

        return orderRefundService.payrefund(pager);

    }
}
