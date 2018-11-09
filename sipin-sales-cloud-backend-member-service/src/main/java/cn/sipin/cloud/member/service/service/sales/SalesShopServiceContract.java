package cn.sipin.cloud.member.service.service.sales;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.sipin.cloud.member.pojo.pojo.salesShop.SalesShop;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.addShop.AddShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.index.IndexShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateShop.UpdateShopRequest;
import cn.sipin.cloud.member.pojo.request.sales.backend.shop.updateSourceIdRequest.SalesUpdateSourceIdRequest;
import cn.sipin.cloud.member.pojo.response.sales.backend.shop.index.IndexShopResponse;
import cn.siyue.platform.base.ResponseData;

/**
 * <p>
 * 门店 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface SalesShopServiceContract extends IService<SalesShop> {

    /**
     * 新增门店
     */
    ResponseData addShop(AddShopRequest addShopRequest);

    /**
     * 删除门店
     */
    ResponseData deleteShop(Long shopId);

    /**
     * 获取指定经销商门店列表
     */
    ResponseData<Page<IndexShopResponse>> index(int page, int size, IndexShopRequest request);

    /**
     * 获取单个门店信息
     */
    ResponseData searchShop(Long shopId);

    /**
     * 修改门店信息
     */
    ResponseData updateShop(Long shopId, UpdateShopRequest updateShopRequest);

    /**
     * 查询所有门店信息
     */
    ResponseData searchAllShop(String type, int page, int size);

    /**
     * 修改门店id
     */
    ResponseData updateSourceId(Long shopId, SalesUpdateSourceIdRequest request);


}
