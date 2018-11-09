package cn.sipin.cloud.member.service.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XStreamUtil {

    private static Logger logger = LoggerFactory.getLogger(XStreamUtil.class);



    public static XStream getXStream(Class clazz) {
        XStream res = new XStream(new MyXpp3DomDriver(nameCoder))
        {

            /**
             * @param next
             * @return
             */
            protected MapperWrapper wrapMapper(MapperWrapper next)
            {
                return createSkipOverElementMapperWrapper(next);
            }



        };

        res.processAnnotations(clazz);
        return res;
    }

    /**
     * 转换过程中特殊字符转码
     */
    private static NameCoder nameCoder = new NameCoder()
    {
        public String encodeNode(String arg0)
        {
            return arg0;
        }

        public String encodeAttribute(String arg0)
        {
            return arg0;
        }

        public String decodeNode(String arg0)
        {
            return arg0;
        }

        public String decodeAttribute(String arg0)
        {
            return arg0;
        }
    };

    /**
     * 在xml中多余的节点生成bean时会抛出异常
     * 通过该mapperWrapper跳过不存在的属性
     * @param mapper
     * @return [参数说明]
     *
     * @return MapperWrapper [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private static MapperWrapper createSkipOverElementMapperWrapper(
            Mapper mapper)
    {
        MapperWrapper resMapper = new MapperWrapper(mapper)
        {
            /**
             * @param elementName
             * @return
             */
            @SuppressWarnings("rawtypes")
            @Override
            public Class realClass(String elementName)
            {
                Class res = null;
                ;
                try
                {
                    res = super.realClass(elementName);
                }
                catch (CannotResolveClassException e)
                {
                    logger.warn("xstream change xml to object. filed (0) not exsit. ",
                            elementName);
                }
                return res;
            }
        };

        return resMapper;
    }
}
