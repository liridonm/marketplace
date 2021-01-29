package com.patela.marketplace.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.patela.marketplace.annotations.MarketPlaceSerializer;
import com.patela.marketplace.domain.entities.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.proxy.HibernateProxyHelper;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class MarketPlaceCustomSerializer extends JsonSerializer<BaseEntity> {

    private List<String> fields = new ArrayList<>();

    @Override
    public void serialize(BaseEntity model, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //Open json start writing
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("id", model.getId());


        Class modelClass = HibernateProxyHelper.getClassWithoutInitializingProxy(model);

        if (modelClass.isAnnotationPresent(MarketPlaceSerializer.class)) {
            Annotation annotation = modelClass.getAnnotation(MarketPlaceSerializer.class);

            MarketPlaceSerializer cinemaSerializerAnn = (MarketPlaceSerializer) annotation;

            fields = Arrays.stream(cinemaSerializerAnn.fields()).collect(Collectors.toList());
        }

        fields.forEach(field -> {
            if (!field.equals("id")) {
                try {
                    Optional<Method> method = getDeclaredMethodForField(model, field);
                    if (method.isPresent()) {
                        Object getNameMethodInvoke = method.get().invoke(model);
                        jsonGenerator.writeObjectField(field, getNameMethodInvoke);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        jsonGenerator.writeEndObject();
    }

    private Optional<Method> getDeclaredMethodForField(BaseEntity model, String field) {
        Method method = null;

        // ex: if name is "name" ---> getName;
        String methodName = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);

        try {
            method = model.getClass().getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            try {
                method = model.getClass().getSuperclass().getDeclaredMethod(methodName);
            } catch (NoSuchMethodException ignored) {
            }
        }

        return Optional.ofNullable(method);
    }
}

