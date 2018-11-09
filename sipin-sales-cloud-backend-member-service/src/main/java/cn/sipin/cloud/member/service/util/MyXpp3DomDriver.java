package cn.sipin.cloud.member.service.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.Xpp3DomDriver;
import java.io.Writer;
import java.lang.reflect.Field;

import cn.sipin.cloud.member.pojo.annotation.XStreamCDATA;

public class MyXpp3DomDriver extends Xpp3DomDriver {


    public MyXpp3DomDriver(NameCoder nameCoder) {
        super(nameCoder);
    }



    @Override
    public HierarchicalStreamWriter createWriter(Writer out) {
        return new PrettyPrintWriter(out, this.getNameCoder()) {
            //输出压缩的xml字符串，删除多余的空白符
            //return new CompactWriter (out, nameCoder) {
            boolean cdata = false;
            Class<?> targetClass = null;
            @Override
            public void startNode(String name, Class clazz) {
                super.startNode(name, clazz);
                if(targetClass == null){
                    targetClass = clazz;
                }
                cdata = needCDATA(targetClass,name);
            }

            @Override
            protected void writeText(QuickWriter writer, String text) {
                if (cdata) {
                    writer.write("<![CDATA[");
                    writer.write(text);
                    writer.write("]]>");
                } else {
                    writer.write(text);
                }
            }
        };
    }

    private static boolean needCDATA(Class<?> targetClass, String fieldAlias){
        boolean cdata = false;
        //first, scan self
        cdata = existsCDATA(targetClass, fieldAlias);
        if(cdata) return cdata;
        //if cdata is false, scan supperClass until java.lang.Object
        Class<?> superClass = targetClass.getSuperclass();
        while(!superClass.equals(Object.class)){
            cdata = existsCDATA(superClass, fieldAlias);
            if(cdata) return cdata;
            superClass = superClass.getSuperclass();
        }
        return false;
    }

    private static boolean existsCDATA(Class<?> clazz, String fieldAlias){
        //scan fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //1. exists XStreamCDATA
            if(field.getAnnotation(XStreamCDATA.class) != null ){
                XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);
                //2. exists XStreamAlias
                if(null != xStreamAlias){
                    if(fieldAlias.equals(xStreamAlias.value()))//matched
                        return true;
                }else{// not exists XStreamAlias
                    if(fieldAlias.equals(field.getName()))
                        return true;
                }
            }
        }
        return false;
    }

}
