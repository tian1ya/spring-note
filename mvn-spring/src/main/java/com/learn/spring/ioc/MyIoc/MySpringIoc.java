package com.learn.spring.ioc.MyIoc;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MySpringIoc {
    private Map<String, Object> ioc = new HashMap<>();
    private List<String> beanNames = new ArrayList<>();

    public MySpringIoc(String pack) {
        Set<BeanDefinition> beanDefinitions = findBeanDefinition(pack);
        createObject(beanDefinitions);
        autowiredObj(beanDefinitions);
    }

    public Object getBean(String beanName) {
        return ioc.get(beanName);
    }

    public String[] getBeanDefinitionNames() {
        return beanNames.toArray(new String[0]);
    }

    public Integer getBeanDefinitionCount() {
        return beanNames.size();
    }

    private void autowiredObj(Set<BeanDefinition> beanDefinitions) {
        Iterator<BeanDefinition> iterator = beanDefinitions.iterator();
        while (iterator.hasNext()) {
            BeanDefinition beanDefinition = iterator.next();
            Class clazz = beanDefinition.getBeanClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Autowired annotation = declaredField.getAnnotation(Autowired.class);
                if (annotation != null) {
                    Qualifier qualifier = declaredField.getAnnotation(Qualifier.class);
                    if (qualifier != null) {
                        //byName
                        try {
                            String beanName = qualifier.value();
                            Object bean = getBean(beanName);
                            String fieldName = declaredField.getName();
                            String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                            Method method = clazz.getMethod(methodName, declaredField.getType());
                            Object object = getBean(beanDefinition.getBeanName());
                            method.invoke(object, bean);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //byType
                    }
                }
            }
        }
    }

    private void createObject(Set<BeanDefinition> beanDefinitions) {
        Iterator<BeanDefinition> iterator = beanDefinitions.iterator();
        while (iterator.hasNext()) {
            BeanDefinition definition = iterator.next();
            Class beanClass = definition.getBeanClass();
            String beanName = definition.getBeanName();
            try {
                Object object = beanClass.getConstructor().newInstance();
                Field[] fields = beanClass.getDeclaredFields();
                for (Field field : fields) {
                    Value valueAnnotation = field.getAnnotation(Value.class);
                    if (valueAnnotation != null) {
                        String value = valueAnnotation.value();
                        String name = field.getName();
                        String methodName = "set" + name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1);
                        Method method = beanClass.getMethod(methodName, field.getType());
                        Object val = null;
                        switch (field.getType().getName()) {
                            case "java.lang.Integer":
                                val = Integer.parseInt(value);
                                break;
                            case "java.lang.String":
                                val = value;
                                break;
                            case "java.lang.Float":
                                val = Float.parseFloat(value);
                                break;
                        }
                        method.invoke(object, val);
                    }
                }
                ioc.put(beanName, object);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    private Set<BeanDefinition> findBeanDefinition(String pack) {
        /*
            1. 获取包下所有得类
            2. 遍历类，并将带有注解得类找到
            3. 封装 BeanDefinitation
         */
        Set<Class<?>> classes = getClzs(pack);
        Iterator<Class<?>> iterator = classes.iterator();
        HashSet<BeanDefinition> beanDefinitions = new HashSet<>();
        while (iterator.hasNext()) {
            Class<?> aClass = iterator.next();
            Component componentAnnotation = aClass.getAnnotation(Component.class);
            String beanName = componentAnnotation.value();
            if ("".endsWith(beanName)) {
                //获取类名首字母小写
                String className = aClass.getName().replaceAll(aClass.getPackage().getName() + ".", "");
                beanName = className.substring(0, 1).toLowerCase(Locale.ROOT) + className.substring(1);
            }
            beanDefinitions.add(new BeanDefinition(beanName, aClass));
            beanNames.add(beanName);
        }
        return beanDefinitions;
    }

    private Set<Class<?>> getClzs(String pack) {
        LinkedHashSet<Class<?>> classes = new LinkedHashSet<>();
        boolean recursive = true;
        String packageDirName = pack.replace(".", "/");

        try {
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findClassInPackageByFile(pack, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    JarFile jars = ((JarURLConnection) (url.openConnection())).getJarFile();
                    Enumeration<JarEntry> entries = jars.entries();
                    findClassesInPackageByJar(pack, entries, packageDirName, recursive, classes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    private void findClassesInPackageByJar(String packageName, Enumeration<JarEntry> entries, String packageDirName, boolean recursive, LinkedHashSet<Class<?>> clzs) {
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            if (name.charAt(0) == '/') {
                name = name.substring(1);
            }
            if (name.startsWith(packageDirName)) {
                int idx = name.lastIndexOf("/");
                if (idx != -1) {
                    packageName = name.substring(0, idx).replace("/", ".");
                }
                if (idx != -1 || recursive) {
                    if (name.endsWith(".class") && !entry.isDirectory()) {
                        String className = name.substring(packageName.length() + 1, name.length() - 0);
                        try {
                            clzs.add(Class.forName(packageName + "." + className));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void findClassInPackageByFile(String packageName, String filePath, boolean recursive, LinkedHashSet<Class<?>> clzs) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) return;

        File[] dirFiles = dir
                .listFiles(pathname -> (recursive && pathname.isDirectory()) || (pathname.getName().endsWith(".class")));

        for (File dirFile : dirFiles) {
            if (dirFile.isDirectory()) {
                findClassInPackageByFile(packageName + "." + dirFile.getName(), dirFile.getAbsolutePath(), recursive, clzs);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = dirFile.getName().substring(0, dirFile.getName().length() - 6);
                try {
                    clzs.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
