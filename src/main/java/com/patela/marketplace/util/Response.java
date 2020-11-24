package com.patela.marketplace.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response {
    private Boolean success;
    private String code;
    private String message;
    private Object data;

    public Response(boolean success) {
        this.success = success;
    }

    public Response(boolean success, String message) {
        this(success);
        this.message = message;
    }

    public Response(boolean success, String message, Object data) {
        this(success, message);
        this.data = data;
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
            return mapper.toString();
        }
    }

    public static Response.ResponseBuilder builder() {
        return new Response.ResponseBuilder();
    }

    public Response() {
    }

    public Response(Boolean success, String code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static class ResponseBuilder {
        private Boolean success;
        private String code;
        private String message;
        private Object data;

        ResponseBuilder() {
        }

        public Response.ResponseBuilder success(Boolean success) {
            this.success = success;
            return this;
        }

        public Response.ResponseBuilder code(String code) {
            this.code = code;
            return this;
        }

        public Response.ResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public Response.ResponseBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public Response build() {
            return new Response(this.success, this.code, this.message, this.data);
        }

        public String toString() {
            return "Response.ResponseBuilder(success=" + this.success + ", code=" + this.code + ", message=" + this.message + ", data=" + this.data + ")";
        }
    }
}
