package cn.wolfcode.wms.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private Long id;

    private String name;

    private String expression;
}