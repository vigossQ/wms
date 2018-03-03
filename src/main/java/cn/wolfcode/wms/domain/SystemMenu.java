package cn.wolfcode.wms.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SystemMenu {
    private Long id;

    private String name;

    private String url;

    private String sn;

    private SystemMenu parent;
}