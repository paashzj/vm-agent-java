package com.github.shoothzj.vm.agent.util;

import com.github.shoothzj.vm.agent.module.SshShellResult;

/**
 * @author hezhangjian
 */
public interface IShellUtil {

    SshShellResult execCommand(String host, int port, String username, String password, String command) throws Exception;

}