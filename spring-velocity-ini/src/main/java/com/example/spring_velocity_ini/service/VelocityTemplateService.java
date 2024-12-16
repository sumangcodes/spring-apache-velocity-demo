package com.example.spring_velocity_ini.service;



import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;

import com.example.spring_velocity_ini.pojo.User;

import java.io.StringWriter;
import java.util.Properties;

@Service
public class VelocityTemplateService {

    private final VelocityEngine velocityEngine;

    public VelocityTemplateService() {
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "file,class");
        properties.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        this.velocityEngine = new VelocityEngine(properties);
    }

    public String generateFromTemplate(User user) {
        VelocityContext context = new VelocityContext();
        context.put("pojo", user);

        StringWriter writer = new StringWriter();
        velocityEngine.getTemplate("templates/template.vm").merge(context, writer);

        return writer.toString();
    }
}
