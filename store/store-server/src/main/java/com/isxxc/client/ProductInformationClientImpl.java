package com.isxxc.client;


import cc.likq.result.Result;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductSearchDTO;
import com.isxxc.service.ProductInformationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 商品详细信息 服务治理实现类
 * </p>
 *
 * @author yk
 * @since 2018-05-17
 */
@RestController
public class ProductInformationClientImpl implements ProductInformationClient {

    @Resource
    private ProductInformationService productInformationService;


    @Override
    public Result getInfoById(Integer id) { return productInformationService.getInfoById(id);
    }

    @Override
    public Result listPage(@RequestBody Pager pager) {
        return productInformationService.listPage(pager);
    }

    @Override
    public Result search(@RequestBody ProductSearchDTO productSearchDTO) { return productInformationService.search(productSearchDTO);
    }


}
