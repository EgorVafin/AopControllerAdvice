package com.example.aop_controlleradvice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.example.aop_controlleradvice.controller"})
@EnableAspectJAutoProxy
public class ProjectConfig {
}
