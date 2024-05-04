package com.riwi.beauty_center.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Api para administrar un centro de belleza",
        version = "1.0",
        description = "Esta api fue creada para el manejo de citas, clientes, servicios y empleados de un salon de belleza"
    )
)
public class OpenApiConfig {

    
}