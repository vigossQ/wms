package cn.wolfcode.wms.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
    private Long id;

    private String name;

    private String sn;

    private String phone;
}