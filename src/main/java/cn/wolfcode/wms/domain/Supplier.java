package cn.wolfcode.wms.domain;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    private Long id;

    private String name;

    private String phone;

    private String address;
}