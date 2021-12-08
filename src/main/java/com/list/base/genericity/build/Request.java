package com.list.base.genericity.build;

import com.alibaba.fastjson.JSONObject;

public class Request<T> {
    private T data;
    private String other;

    public T getData() {
        return data;
    }

    public String getOther() {
        return other;
    }

    public static <T>  Builder<T> builder(){
        return new Builder<>();
    }

    public static class Builder<T> {
        private T data;
        private String other;



        public Builder data(T data){
            this.data = data;
            return this;
        }

        public Builder other(String other){
            this.other = other;
            return this;
        }

        public Request<T> build(){
            Request<T> request = new Request<>();
            request.data = this.data;
            request.other = this.other;
            return request;
        }

    }

    public static void main(String[] args) {
        Request<Integer> request = Request.builder().data(10).other("test").build();
        /*System.out.println(request.getData());
        System.out.println(request.getOther());*/
        System.out.println(JSONObject.toJSONString(request));
    }
}
