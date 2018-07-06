package com.isxxc.client;


import cc.likq.result.Result;
import com.isxxc.domain.dto.*;
import com.isxxc.domain.entity.OrderRefundDO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单退款 服务治理
 * </p>
 *
 * @author mc
 * @since 2018-05-10
 */
@RequestMapping("/orderRefundClient")
public interface OrderRefundClient {


    /***
     * 客户申请订单退款
     * @param orderRefundListDTO
     * @return
     */
    @RequestMapping(value = "consumerApplyRefund", method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result consumerApplyRefund(OrderRefundListDTO orderRefundListDTO);


    /***
     * 退款信息列表查询
     * @param pager
     * @return
     */
    @RequestMapping(value = "consumerRefundPagelist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result consumerRefundPagelist(Pager pager);


    /***
     * 后台审核退款单据列表查询
     * @param pager
     * @return listRefundPage(@RequestBody Map<String ,Object> models)
     */
    @RequestMapping(value = "listRefundPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listRefundPage(Pager pager);

    /***
     * 商家第一次退款审核
     * @param orderRefundDTO
     * @return
     */
    @RequestMapping(value = "sellerFirstAuditRefund", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result sellerFirstAuditRefund(OrderRefundDTO orderRefundDTO);

    /***
     * 后台审核商家退款订单
     * @param orderRefundListDTO
     * @return
     */
    @RequestMapping(value = "backstageAuditRefund", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result backstageAuditRefund(OrderRefundListDTO orderRefundListDTO);







    /***
     * 订单退款
     * @param pager
     * @return
     */
    @RequestMapping(value = "payrefund", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result payrefund(Pager pager);


    /***
     * 微信支付回调
     * @param paramsMap
     * @return
     */
    @RequestMapping(value = "wxRefundResult", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result wxRefundResult(Map<String, String> paramsMap);


}
