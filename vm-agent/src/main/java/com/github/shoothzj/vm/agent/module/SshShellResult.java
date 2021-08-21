package com.github.shoothzj.vm.agent.module;

import lombok.Data;

/**
 * @author hezhangjian
 */
@Data
public class SshShellResult {

    private int returnCode;

    private String outputContent;

    private String errorContent;

    public SshShellResult() {
    }

    @Override
    public String toString() {
        return "SshShellResult{" +
                "returnCode=" + returnCode +
                ", outputContent='" + outputContent + '\'' +
                ", errorContent='" + errorContent + '\'' +
                '}';
    }
}
