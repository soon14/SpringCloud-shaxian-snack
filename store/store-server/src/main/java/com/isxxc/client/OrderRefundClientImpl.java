package com.isxxc.client;


import cc.likq.result.Result;
import com.isxxc.domain.dto.*;
import com.isxxc.service.OrderInfoService;
import com.isxxc.service.OrderRefundService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单退款 服务治理
 * </p>
 *
 * @author mc
 * @since 2018-05-11
 */
@RestController
public class OrderRefundClientImpl implements OrderRefundClient {

    @Resource
    private OrderRefundService orderRefundService;

    /***
     * 客客户申请订单退款
     * @param orderRefundListDTO
     * @return
     */
    @Override
    public Result consumerApplyRefund(@RequestBody OrderRefundListDTO orderRefundListDTO) {
        return orderRefundService.consumerApplyRefund(orderRefundListDTO);
    }

    /***
     * 退款信息列表查询
     * @param pager
     * @return
     */
    @Override
    public Result consumerRefundPagelist(@RequestBody Pager pager) {
        return orderRefundService.consumerRefundPagelist(pager);
    }

    /***
     * 后台退款信息列表查询
     * @param pager
     * @return
     */
    @Override
    public Result listRefundPage(@RequestBody Pager pager) {
        return orderRefundService.listRefundPage(pager);
    }


    /***
     * 商家第一次退款审核
     * @param orderRefundDTO
     * @return
     */
    @Override
    public Result sellerFirstAuditRefund(@RequestBody OrderRefundDTO orderRefundDTO) {
        return orderRefundService.sellerFirstAuditRefund(orderRefundDTO);
    }


    /***
     * 后台审核商家审核后退款订单
     * @param orderRefundListDTO
     * @return
     */
    @Override
    public Result backstageAuditRefund(@RequestBody OrderRefundListDTO orderRefundListDTO) {
        return orderRefundService.backstageAuditRefund(orderRefundListDTO);
    }



    /***
     * 后台向支付发起退款
     * @param pager
     * @return
     */
    @Override
    public Result payrefund(@RequestBody  Pager pager) {
        return orderRefundService.orderPayRefund(pager);
    }


    /***
     * 后台审核商家审核后退款订单
     * @param paramsMap
     * @return
     */
    @Override
    public Result wxRefundResult(@RequestBody Map<String, String> paramsMap) {
        return orderRefundService.wxRefundResult(paramsMap);
    }

}
