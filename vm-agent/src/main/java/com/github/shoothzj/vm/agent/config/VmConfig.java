package com.github.shoothzj.vm.agent.config;

import com.github.shoothzj.javatool.util.EnvUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class VmConfig {

    public static final String VM_HOST = EnvUtil.getStringVar("vm.host", "VM_HOST", "localhost");

    public static final int VM_PORT = EnvUtil.getIntVar("vm.port", "VM_PORT", 22);

    public static final String SSH_USER = EnvUtil.getStringVar("ssh.user", "SSH_USER", "root");

    public static final String SSH_PASSWORD = EnvUtil.getStringVar("ssh.password", "SSH_PASSWORD", "toor");


}
