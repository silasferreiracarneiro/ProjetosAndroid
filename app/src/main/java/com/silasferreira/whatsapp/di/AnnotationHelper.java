package com.silasferreira.whatsapp.di;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface AnnotationHelper {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @interface LoginRepository{}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @interface CadastroRepository{}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @interface SettingRepository{}
}
