package cn.wolfcode.wms.domain;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;

    private String name;

    private String sn;

    private BigDecimal costPrice;

    private BigDecimal salePrice;

    private String imagePath;

    private String intro;

    private Long brandId;

    private String brandName;

    public String getSmallImagePath() {
        if (!StringUtils.isEmpty(imagePath)) {
            int index = imagePath.lastIndexOf(".");
            return imagePath.substring(0, index) + "_small" +
                    imagePath.substring(index);
        } else {
            return null;
        }
    }

    public String getProductData() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("costPrice", costPrice);
        map.put("salePrice", salePrice);
        map.put("brandName", brandName);
        map.put("imagePath", imagePath);
        return JSON.toJSONString(map);
    }
}