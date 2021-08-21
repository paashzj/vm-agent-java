package com.github.shoothzj.vm.agent.module;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hezhangjian
 */
@Data
@AllArgsConstructor
public class ProcModule {

    private String user;

    private int pid;

    public ProcModule() {
    }
}
