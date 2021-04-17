package com.example.springcloud.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {
    @Getter
    @Setter
    private Long deptno;
    @Getter
    @Setter
    private String dname;
    @Getter
    @Setter
    private String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }


}
