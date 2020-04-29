package com.panda.esportingplus.common.tools;

import java.util.HashMap;
import java.util.Map;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 表达式计算
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class ExpressJEval {
    static public Object calculate(String expressJ, Map<String,Object> params){
        try {
            ScriptEngine se = new ScriptEngineManager().getEngineByName("js");
            Compilable ce = (Compilable) se;
            CompiledScript cs = ce.compile(expressJ);
            Bindings bindings = se.createBindings();
            for(String key:params.keySet()){
                bindings.put(key, params.get(key));
            }
            return cs.eval(bindings);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("x", 5);
        System.out.println(calculate("5<=x<=5", map));
    }

}
