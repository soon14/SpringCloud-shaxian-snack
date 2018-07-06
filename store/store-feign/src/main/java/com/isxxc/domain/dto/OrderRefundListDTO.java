package com.isxxc.domain.dto;

import com.isxxc.domain.entity.OrderRefundDO;

import java.util.Date;
import java.util.List;


/**
 * <p>
 *添加退款信息实体集合，做入参使用
 * </p>
 *
 * @author 吴坚
 * @since 2018-5-9
 */
public class OrderRefundListDTO  {

    /***
     * 订单商品详细信息列表
     */
    private List<OrderRefundDTO> listorderRefundDTO;

    public OrderRefundListDTO() {
    }

    public OrderRefundListDTO(List<OrderRefundDTO> listorderRefundDTO) {
        this.listorderRefundDTO = listorderRefundDTO;
    }

    public List<OrderRefundDTO> getListorderRefundDTO() {
        return listorderRefundDTO;
    }

    public void setListorderRefundDTO(List<OrderRefundDTO> listorderRefundDTO) {
        this.listorderRefundDTO = listorderRefundDTO;
    }

    @Override
    public String toString() {
        return "OrderRefundListDTO{" +
                "listorderRefundDTO=" + listorderRefundDTO +
                '}';
    }
}





