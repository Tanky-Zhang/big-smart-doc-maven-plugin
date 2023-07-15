package com.smartdoc.mojo;

import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;

import com.power.doc.builder.rpc.grpc.GRpcHtmlBuilder;
import com.power.doc.model.ApiConfig;
import com.smartdoc.constant.MojoConstants;
import com.thoughtworks.qdox.JavaProjectBuilder;

/**
 * @author zhangzhongguo
 * Created on 2023-07-10
 */
@Execute(phase = LifecyclePhase.COMPILE)
@Mojo(name = MojoConstants.GRPC_HTML_MOJO, requiresDependencyResolution = ResolutionScope.COMPILE)
public class GRpcHtmlMojo extends BaseDocsGeneratorMojo {


    @Override
    public void executeMojo(ApiConfig apiConfig, JavaProjectBuilder javaProjectBuilder) {
        try {
            GRpcHtmlBuilder.buildApiDoc(apiConfig, javaProjectBuilder);
        } catch (Throwable e) {
            getLog().error(e);
            if (apiConfig.isStrict()) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
