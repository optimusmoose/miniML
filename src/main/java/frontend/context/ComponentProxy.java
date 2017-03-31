package frontend.context;

import utils.Logging.MiniMLLogger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ComponentProxy implements InvocationHandler {

    private Object obj;

    public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new ComponentProxy(obj)
        );
    }

    private ComponentProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try {
            MiniMLLogger.INSTANCE.debug("before method " + method.getName());
            result = method.invoke(obj, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            String message = "unexpected invocation exception: " + e.getMessage();
            MiniMLLogger.INSTANCE.error(message);
            throw new RuntimeException(message);
        } finally {
            MiniMLLogger.INSTANCE.debug("after method " + method.getName());
        }
        return result;
    }
}
