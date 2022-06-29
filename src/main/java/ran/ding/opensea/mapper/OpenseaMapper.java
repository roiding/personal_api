package ran.ding.opensea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ran.ding.opensea.entity.Asset;

import java.util.List;

@Mapper
public interface OpenseaMapper {
    /**
     * 获得所有的资产信息
     */
    @Select("select name, image_url, token_id from opensea_asset")
    List<Asset> getAllAssets();
}
