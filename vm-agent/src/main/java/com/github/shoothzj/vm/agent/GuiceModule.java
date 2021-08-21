package com.github.shoothzj.vm.agent;

import com.github.shoothzj.vm.agent.config.VmConfig;
import com.github.shoothzj.vm.agent.service.ISshService;
import com.github.shoothzj.vm.agent.service.SshService;
import com.github.shoothzj.vm.agent.util.IShellUtil;
import com.github.shoothzj.vm.agent.util.JschShellUtil;
import com.github.shoothzj.vm.agent.util.MinaShellUtil;
import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();
        bindServicePackage();
        bindUtilPackage();
    }

    private void bindServicePackage() {
        bind(ISshService.class).to(SshService.class);
    }

    private void bindUtilPackage() {
        if (VmConfig.SSH_CLIENT.equals("MINA")) {
            bind(IShellUtil.class).to(MinaShellUtil.class);
        } else {
            bind(IShellUtil.class).to(JschShellUtil.class);
        }
    }

}
