package com.kfoodsys.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author phuho
 */
public class ObjectFile {
    private String file;
    private Object[] obj;

    private void writeObjectPrivate() throws IOException {
        // ghi file
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Object a:obj){
            oos.writeObject(a.toString());
        }
        oos.close();
        fos.close();
    }

    private String readObjectPrivate() throws IOException, ClassNotFoundException {
        String data = "";
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (fis.available() > 0) {
            data += ois.readObject() + "\n";
        }
        ois.close();
        fis.close();
        return data;
    }

    public void writeObject(Object...obj) throws IOException {
        this.obj = obj;
        writeObjectPrivate();
    }

    public String readObject() throws IOException, ClassNotFoundException {
        String result = readObjectPrivate();
        return result;
    }

    /**
     * Phuong thuc cu
     * @param ObjectFile
     * @deprecated không nên dùng cái này, nên truyền trực tiếp đường dẫn vào luôn!
     */
    public ObjectFile(){

    }

    public ObjectFile(String file) throws IOException {
        this.file = file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
