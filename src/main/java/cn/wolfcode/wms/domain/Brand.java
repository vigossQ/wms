package cn.wolfcode.wms.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    private Long id;

    private String name;

    private String sn;
}