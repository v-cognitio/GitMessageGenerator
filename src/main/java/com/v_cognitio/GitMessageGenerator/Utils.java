package com.v_cognitio.GitMessageGenerator;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

public class Utils {

    private static VelocityEngine engine;


    static {
        engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.PARSER_POOL_SIZE, 20);
        engine.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
        engine.setProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");

        Properties props = new Properties();
        props.put("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
        props.put("runtime.log.logsystem.log4j.category", "velocity");
        props.put("runtime.log.logsystem.log4j.logger", "velocity");

        engine.init(props);
    }

    public static String convert(String template, CommitTemplate commitTemplate) {
        StringWriter writer = new StringWriter();
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("type", commitTemplate.getType());
        velocityContext.put("scope", commitTemplate.getScope());
        velocityContext.put("subject", commitTemplate.getSubject());
        velocityContext.put("body", commitTemplate.getBody());
        velocityContext.put("changes", commitTemplate.getChanges());
        velocityContext.put("closes", commitTemplate.getCloses());
        velocityContext.put("newline", "\n");
        String VM_LOG_TAG = "Leetcode Utils";
        boolean isSuccess = engine.evaluate(velocityContext, writer, VM_LOG_TAG, template);

        if (!isSuccess) {
            return null;
        }
        return writer.toString();
    }

    public static void loadProperties(InputStream stream, Settings settings) {
        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            throw new IllegalArgumentException("Such a property path doesn't exist");
        }

        settings.messageTemplate = properties.getProperty("MESSAGE_TEMPLATE");
    }
}