package com.isxxc.service;

import cc.likq.result.Result;
import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.OrderRefundDTO;
import com.isxxc.domain.dto.OrderRefundListDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.OrderRefundDO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 订单退款 服务类
 * </p>
 *
 * @author mc
 * @since 2018-05-10
 */
public interface OrderRefundService extends IService<OrderRefundDO> {

    /***
     * 客户申请订单退款
     * @param orderRefundListDTO
     * @return
     */
    Result consumerApplyRefund(OrderRefundListDTO orderRefundListDTO);

    /***
     * 客户退款信息查询
     * @param pager
     * @return
     */
    Result consumerRefundPagelist(Pager pager);


    /***
     * 后台退款信息列表查询
     * @param pager
     * @return
     */
    Result listRefundPage(Pager pager);

    /***
     * 商家第一次退款审核
     * @param orderRefundDTO
     * @return
     */
    Result sellerFirstAuditRefund(OrderRefundDTO orderRefundDTO);

    /***
     * 后台审核商家审核后退款订单
     * @param orderRefundListDTO
     * @return
     */
    Result backstageAuditRefund(OrderRefundListDTO orderRefundListDTO);


    /***
     * 订单退款
     * @param pager
     * @return
     */
    Result orderPayRefund(Pager pager);


    /***
     * 微信退款回调
     * @param paramsMap
     * @return
     */
    Result wxRefundResult(Map<String, String> paramsMap);





}
