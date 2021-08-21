package com.github.shoothzj.vm.agent.service;

import com.github.shoothzj.vm.agent.config.VmConfig;
import com.github.shoothzj.vm.agent.constant.Const;
import com.github.shoothzj.vm.agent.module.ProcModule;
import com.github.shoothzj.vm.agent.module.SshShellResult;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hezhangjian
 */
@Slf4j
public class ProcListService {

    @Inject
    private SshService sshService;

    public List<ProcModule> listProc() throws Exception {
        final String[] outputArray = sshService.execCommandFailException("ps -ef");
        List<ProcModule> procModuleList = new ArrayList<>();
        for (int i = 1; i < outputArray.length; i++) {
            String procLine = outputArray[i];
            final String[] split = procLine.split("\\s+");
            procModuleList.add(new ProcModule(split[0], Integer.parseInt(split[1])));
        }
        return procModuleList;
    }

}
