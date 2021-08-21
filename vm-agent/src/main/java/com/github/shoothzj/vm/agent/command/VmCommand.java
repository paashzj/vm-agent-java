package com.github.shoothzj.vm.agent.command;

import com.github.shoothzj.vm.agent.module.ProcModule;
import com.github.shoothzj.vm.agent.service.ProcListService;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author hezhangjian
 */
@Slf4j
public class VmCommand implements Runnable {

    @Inject
    private ProcListService procListService;

    public VmCommand() {
    }

    @Override
    public void run() {
        try {
            log.info("begin to execute");
            final List<ProcModule> procModules = procListService.listProc();
            log.info("list proc size is {}", procModules.size());
        } catch (Exception e) {
            log.error("exec error ", e);
        }
    }
}
