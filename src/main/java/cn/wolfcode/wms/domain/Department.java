package cn.wolfcode.wms.domain;

import lombok.*;

@ToString@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Department {
    private Long id;

    private String name;

    private String sn;
}