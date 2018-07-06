package com.isxxc.web;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import com.alibaba.fastjson.JSON;
import com.isxxc.client.OrderItemCommentClient;
import com.isxxc.domain.dto.OrderItemCommentDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.service.feign.store.OrderItemCommentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/orderItemComment")
public class OrderItemCommentController {

    @Resource
    private OrderItemCommentService orderItemCommentService;
    @Resource
    private OrderItemCommentClient orderItemCommentClient;

    Logger logger = LogManager.getLogger(OrderItemCommentController.class);
    /**
     * 查询商家所有评价
     * @param pager
     * @param storeId
     * @return
     */
    @RequestMapping("getInfoByStoreId")
    public Result getInfoByStoreId(Pager pager,Integer storeId){
        logger.info("查询商家所有评价:getInfoByStoreId:"+pager+"/storeId:"+storeId);
        if(storeId == null){
            return ResponseResult.paramNotNull("商家ID不能为空");
        }
        pager.putParam("storeId",storeId);
        return orderItemCommentService.getInfoByStoreId(pager,storeId);
    }

    /**
     * 商家回复
     * @param orderItemCommentDTO
     * @return
     */
    @RequestMapping("replyComments")
    public Result replyComments(@RequestBody OrderItemCommentDTO orderItemCommentDTO){
        logger.info("商家回复入参："+JSON.toJSONString(orderItemCommentDTO));
        if(orderItemCommentDTO.getId()==null){
            return ResponseResult.paramNotNull("ID不能为空");
        }
        if(StringUtils.isEmpty(orderItemCommentDTO.getReply())){
            return ResponseResult.paramNotNull("回复类容不能为空");
        }
        return orderItemCommentService.replyComments(orderItemCommentDTO);
    }
}
