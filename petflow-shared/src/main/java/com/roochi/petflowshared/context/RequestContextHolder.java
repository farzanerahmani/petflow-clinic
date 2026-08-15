package com.roochi.petflowshared.context;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public final class RequestContextHolder {
    private RequestContextHolder(){}

    private static final ThreadLocal<RequestContext> CONTEXT = new ThreadLocal<>();

    public static void set(RequestContext context){
        CONTEXT.set(context);
    }

    public static RequestContext get(){
        return CONTEXT.get();
    }
    public static void clear(){
        CONTEXT.remove();
    }
}
