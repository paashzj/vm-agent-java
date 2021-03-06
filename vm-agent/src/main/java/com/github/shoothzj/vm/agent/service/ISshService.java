package com.github.shoothzj.vm.agent.service;

import com.github.shoothzj.vm.agent.module.SshShellResult;

/**
 * @author hezhangjian
 */
public interface ISshService {

    SshShellResult execCommand(String command) throws Exception;

    String[] execCommandFailException(String command) throws Exception;

}
