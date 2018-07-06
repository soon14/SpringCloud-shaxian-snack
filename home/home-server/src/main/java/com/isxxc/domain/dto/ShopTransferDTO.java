package com.isxxc.domain.dto;

import com.isxxc.domain.entity.ShopTransferDO;

import java.util.List;

import cc.likq.util.MyBeanUtils;

/**
 * @author 泥水佬
 * @date 2017/12/7
 */
public class ShopTransferDTO extends ShopTransferDO {

    /***
     * 租金查范围询开始
     */
    private Integer rentAmountStart;
    /***
     * 租金范围查询结束
     */
    private Integer rentAmountEnd;
    /***
     * 转让费范围查询开始
     */
    private Integer transferAmountStart;
    /***
     * 转让费范围查询结束
     */
    private Integer transferAmountEnd;
    /***
     *面积范围查询开始
     */
    private Float acreageStart;
    /***
     *面积范围查询开始
     */
    private Float acreageEnd;
    /***
     * 排序类型(0:面积,1:月租金,2:最新)
     */
    private Integer sortType;
    /***
     *排序顺序(0:顺序,1:反序)
     */
    private Integer sortAsc = 0;
    /***
     * 图片集
     */
    private List<ShopTransferImgDTO> imgList;

    public ShopTransferDTO() {
    }

    public ShopTransferDTO(ShopTransferDO shopTransferDO) {
        try {
            MyBeanUtils.copyBeanNotNull2Bean(shopTransferDO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ShopTransferImgDTO> getImgList() {
        return imgList;
    }

    public void setImgList(List<ShopTransferImgDTO> imgList) {
        this.imgList = imgList;
    }

    public Integer getRentAmountStart() {
        return rentAmountStart;
    }

    public void setRentAmountStart(Integer rentAmountStart) {
        this.rentAmountStart = rentAmountStart;
    }

    public Integer getRentAmountEnd() {
        return rentAmountEnd;
    }

    public void setRentAmountEnd(Integer rentAmountEnd) {
        this.rentAmountEnd = rentAmountEnd;
    }

    public Integer getTransferAmountStart() {
        return transferAmountStart;
    }

    public void setTransferAmountStart(Integer transferAmountStart) {
        this.transferAmountStart = transferAmountStart;
    }

    public Integer getTransferAmountEnd() {
        return transferAmountEnd;
    }

    public void setTransferAmountEnd(Integer transferAmountEnd) {
        this.transferAmountEnd = transferAmountEnd;
    }

    public Float getAcreageStart() {
        return acreageStart;
    }

    public void setAcreageStart(Float acreageStart) {
        this.acreageStart = acreageStart;
    }


    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public Integer getSortAsc() {
        return sortAsc;
    }

    public void setSortAsc(Integer sortAsc) {
        this.sortAsc = sortAsc;
    }

    public Float getAcreageEnd() {
        return acreageEnd;
    }

    public void setAcreageEnd(Float acreageEnd) {
        this.acreageEnd = acreageEnd;
    }

    @Override
    public String toString() {
        return "ShopTransferDTO{" +
                "imgList=" + imgList +
                '}';
    }
}
