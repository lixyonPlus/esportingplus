package com.panda.esportingplus.common.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.python.core.Py;
import org.python.core.PyBaseString;
import org.python.core.PyFunction;

import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

@Slf4j
public abstract class Pickle {

    static {
        boolean gcMonitorGlobal = PyBaseString.gcMonitorGlobal;
        Properties props = new Properties();
        props.put("python.home", "path to the Lib folder");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        PySystemState sys = Py.getSystemState();
        sys.path.add("C:\\Program Files\\Python37\\Lib");
    }

    public static void main(String[] args) {
        String dumps = pyPickle("['aa', 'bb', 'cc']", true);
        System.out.println(dumps);
        System.out.println(pyPickle(dumps, false));
    }

    /**
     *@Description:
     * data 数据源
     * dumps true=dumps false=loads
     *@author  Orochi-Yzh
     *@dateTime  10/10/2019 10:05 PM
     */
    public static String pyPickle(String data,boolean dumps){
        PythonInterpreter interpreter = new PythonInterpreter();
        String script = "import pickle\n"
                + "def dumps(data):\n"
                + "  return pickle.dumps(data)\n"
                + "def loads(data):\n"
                + "  return pickle.loads(data)";
        interpreter.exec(script);
        PyFunction func_dumps = interpreter.get("dumps", PyFunction.class);
        PyFunction func_loads = interpreter.get("loads", PyFunction.class);

        if(dumps){
            return func_dumps.__call__(new PyString(data)).toString();
        }else{
            return func_loads.__call__(new PyString(data)).toString();
        }
    }

    /**
     * Method that serializes the data in a file with append writting mode or not.
     * pickle.loads(premade_data)  pickle.dumps(premade_data))
     * @param o the object to be serialized.
     * @param f the file where the serialization will occur.
     * @param append sets the writting mode in the file to the append mode or not.
     */
    public static void dump(Object o, File f, boolean append) {
        if (o != null && f != null) {
            try {
                // File where the data will be serialized.
                FileOutputStream fos = new FileOutputStream(f, append);
                // Object responsible for writing the data in the file.
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.flush();
                // Writting the data.
                oos.writeObject(o);
                oos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method that serializes the data in a file.
     *
     * @param o the object to be serialized.
     * @param f the file where the serialization will occur.
     */
    public static void dump(Object o, File f) {
        dump(o, f, false);
    }

    /**
     * Method that serializes and stores an object in memory.
     * Then returns a string representing the serialized object.
     *
     * @param o the object to be serialized.
     * @return a string representing the serialized object.
     */
    public static String dumps(Object o) {
        String s = "";
        try {
            // Serializing.
            // Reference to a byte sequence in the memory.
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Reference to the serializer.
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // Serializing the object.
            oos.writeObject(o);
            // Closing the serializer.
            oos.close();
            // Converting a byte array to a string.
            s = new String(Base64.encodeBase64(baos.toByteArray()));
            // Closing the byte array.
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * Method that deserializes an object in a specific way.
     * This method converts an object to a specific class type.
     *
     * @param f file where serialized object was stored.
     * @param c class of the object that will be returned.
     * @param <T> generic type
     * @return the deserialized object.
     */
    public static <T> T load(File f, Class<T> c) {
        Object o = null;
        if (f != null && c != null) {
            if (f.exists() && f.isFile()) {
                try {
                    // Creating an input stream of data from a file.
                    FileInputStream fis = new FileInputStream(f);
                    // Creating an object that read data from a file.
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    // Reading the data.
                    o = ois.readObject();
                    // Closing the reader.
                    ois.close();
                    // Closing the input stream.
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return c.cast(o);
    }

    /**
     * Method that deserialize an object in generic way.
     *
     * @param f file where serialized object was stored.
     * @return the deserialized object.
     */
    public static Object load(File f) {
        return load(f, Object.class);
    }

    public static <T> T loads(String s, Class<T> c) {
        Object o = null;
        ByteArrayInputStream bais;
        try {
            // Creating a input stream of data that comes from an array of bytes.
            bais = new ByteArrayInputStream(Base64.decodeBase64(s.getBytes()));
            // Creating reader object.
            ObjectInputStream ois = new ObjectInputStream(bais);
            // Reading the data.
            o = ois.readObject();
            // Closing the reader.
            ois.close();
            // Closing the byte array.
            bais.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c.cast(o);
    }

    /**
     * Method that deserializes a object from a string.
     *
     * @param s string that represents the serialized object.
     * @return the deserialized object.
     */
    public static Object loads(String s) {
        return loads(s, Object.class);
    }


}
