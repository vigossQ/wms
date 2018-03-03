package cn.wolfcode.wms.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Depot {
    private Long id;

    private String name;

    private String location;
}