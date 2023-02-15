package ran.ding.opensea.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ran.ding.opensea.entity.Asset;
import ran.ding.opensea.mapper.OpenseaMapper;
import ran.ding.result.ResponseResult;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/opensea")
public class OpenseaController {
    @Resource
    private OpenseaMapper mapper;
    @GetMapping("/getAssetsInfo")
    public ResponseResult getAssetsInfo() {
        List<Asset> allAssets = mapper.getAllAssets();
        return ResponseResult.success(allAssets);
    }
}
