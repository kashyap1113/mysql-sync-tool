package com.kashyap1113.mysqlsynctool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleScriptContext;

public class PythonScriptExecutor {
    public String executeScript(String scriptPath, String... args) {
        
        List<String> argsList = new ArrayList<String>();
        argsList.add("python");
        argsList.add(scriptPath);
        argsList.addAll(Arrays.asList(args));
        args = argsList.toArray(new String[argsList.size()]);
        ProcessBuilder pb = new ProcessBuilder(args);
        Process p = null;
        StringBuilder output = new StringBuilder();
        try {
            p = pb.start();
            p.waitFor();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String currentLine = "";
            while ((currentLine = in.readLine()) != null) {
                output.append(currentLine + "\n");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output.toString();
    }

    public String executeScriptJython(String scriptPath) {
        StringWriter writer = new StringWriter(); // ouput will be stored here

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptContext context = new SimpleScriptContext();

        context.setWriter(writer); // configures output redirection
        ScriptEngine engine = manager.getEngineByName("python");
        try {
            engine.eval(new FileReader(scriptPath), context);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return writer.toString();
    }
}
