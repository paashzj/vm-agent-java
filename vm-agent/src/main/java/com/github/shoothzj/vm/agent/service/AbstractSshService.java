package com.github.shoothzj.vm.agent.service;

import com.github.shoothzj.vm.agent.constant.Const;
import com.github.shoothzj.vm.agent.module.SshShellResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public abstract class AbstractSshService implements ISshService {

    @Override
    public abstract SshShellResult execCommand(String command) throws Exception;

    @Override
    public String[] execCommandFailException(String command) throws Exception {
        final SshShellResult sshShellResult = execCommand(command);
        if (sshShellResult.getReturnCode() != 0) {
            throw new Exception("exec shell exception");
        }
        return sshShellResult.getOutputContent().split(Const.SHELL_SPLIT);
    }
}
