package cn.wolfcode.wms.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long id;

    private String name;

    private String sn;

    private List<Permission> permissions = new ArrayList<>();

    private List<SystemMenu> systemMenus = new ArrayList<>();
}