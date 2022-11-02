package com.finvisage.listeners;


import com.finvisage.utils.DataProviderUtils;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public  void transform(ITestAnnotation annotation, Class testClass, Constructor testonstructor, Method testMethod) {


        annotation.setDataProvider("getData");
        annotation.setDataProviderClass(DataProviderUtils.class);
        annotation.setRetryAnalyzer(RetryFailedTest.class);



    }

}
