package com.springmvc.utils;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;  
import java.beans.Introspector;  
import java.beans.PropertyDescriptor;  
import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method;  
import java.util.HashMap;  
import java.util.Map;  
import java.util.TreeMap;  

public class MapEntryConvet {
	 /** 
	     * ��һ�� Map ����ת��Ϊһ�� JavaBean 
	     * @param clazz Ҫת��������
	     * @param map ��������ֵ�� map
	     * @return ת�������� JavaBean ����
	     * @throws IntrospectionException �������������ʧ�� 
	    * @throws IllegalAccessException ���ʵ���� JavaBean ʧ�� 
	     * @throws InstantiationException ���ʵ���� JavaBean ʧ�� 
	     * @throws InvocationTargetException ����������Ե� setter ����ʧ�� 
	     */  
	    @SuppressWarnings("rawtypes")  
	    public static <T> T toBean(Class<T> clazz, Map map) {
	       T obj = null;
	        try {  
	           BeanInfo beanInfo = Introspector.getBeanInfo(clazz);  
	           obj = clazz.newInstance(); // ���� JavaBean ����  
	 
	           // �� JavaBean ��������Ը�ֵ  
	            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
	           for (int i = 0; i < propertyDescriptors.length; i++) {  
	               PropertyDescriptor descriptor = propertyDescriptors[i];  
	               String propertyName = descriptor.getName();  
	               if (map.containsKey(propertyName)) {  
	                  // ����һ����� try ������������һ�����Ը�ֵʧ�ܵ�ʱ��Ͳ���Ӱ���������Ը�ֵ��  
	                   Object value = map.get(propertyName);  
	                   if ("".equals(value)) {  
	                       value = null;  
	                  }  
	                   Object[] args = new Object[1];  
	                  args[0] = value;  
	                  try {  
	                        descriptor.getWriteMethod().invoke(obj, args);  
	                    } catch (InvocationTargetException e) {  
	                        System.out.println("�ֶ�ӳ��ʧ��");  
	                    }  
	                }  
	            }  
	        } catch (IllegalAccessException e) {  
	            System.out.println("ʵ���� JavaBean ʧ��");  
	        } catch (IntrospectionException e) {  
	            System.out.println("����������ʧ��");  
	        } catch (IllegalArgumentException e) {  
            System.out.println("ӳ�����");  
	        } catch (InstantiationException e) {  
	            System.out.println("ʵ���� JavaBean ʧ��");  
	        }  
	        return (T) obj;
	    }  
	  
	    /** 
	     * ��һ�� JavaBean ����ת��Ϊһ�� Map 
	     * @param bean Ҫת����JavaBean ���� 
	     * @return ת�������� Map ���� 
	     * @throws IntrospectionException �������������ʧ�� 
	     * @throws IllegalAccessException ���ʵ���� JavaBean ʧ�� 
	     * @throws InvocationTargetException ����������Ե� setter ����ʧ�� 
	     */  
	    @SuppressWarnings("rawtypes")  
	    public static Map toMap(Object bean) {  
	        Class<? extends Object> clazz = bean.getClass();  
	        Map<Object, Object> returnMap = new HashMap<Object, Object>();  
	        BeanInfo beanInfo = null;  
	        try {  
	          beanInfo = Introspector.getBeanInfo(clazz);  
	            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
	            for (int i = 0; i < propertyDescriptors.length; i++) {  
	                PropertyDescriptor descriptor = propertyDescriptors[i];  
	                String propertyName = descriptor.getName();  
	                if (!propertyName.equals("class")) {  
	                    Method readMethod = descriptor.getReadMethod();  
	                    Object result = null;  
	                    result = readMethod.invoke(bean, new Object[0]);  
	                    if (null != propertyName) {  
	                        propertyName = propertyName.toString();  
	                    }  
	                    if (null != result) {  
	                        result = result.toString();  
	                    }  
	                    returnMap.put(propertyName, result);  
	                }  
	            }  
	        } catch (IntrospectionException e) {  
	            System.out.println("����������ʧ��");  
	        } catch (IllegalAccessException e) {  
	            System.out.println("ʵ���� JavaBean ʧ��");  
	        } catch (IllegalArgumentException e) {  
	            System.out.println("ӳ�����");  
	        } catch (InvocationTargetException e) {  
	            System.out.println("�������Ե� setter ����ʧ��");  
	        }  
	        return returnMap;  
	    }  
	  
	

}
