package com.github.shoothzj.vm.agent.service;

import com.github.shoothzj.vm.agent.config.VmConfig;
import com.github.shoothzj.vm.agent.module.SshShellResult;
import com.github.shoothzj.vm.agent.util.IShellUtil;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class SshService extends AbstractSshService {

    @Inject
    private IShellUtil shellUtil;

    public SshService() {
    }

    @Override
    public SshShellResult execCommand(String command) throws Exception{

        return shellUtil.execCommand(VmConfig.SSH_HOST, VmConfig.SSH_PORT,
                VmConfig.SSH_USER, VmConfig.SSH_PASSWORD, command);
    }
}
