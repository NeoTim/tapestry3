package org.apache.tapestry.engine;

import edu.emory.mathcs.backport.java.util.concurrent.locks.ReentrantLock;
import ognl.ClassCacheInspector;
import ognl.Node;
import ognl.OgnlRuntime;
import org.apache.tapestry.AbstractComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Howard M. Lewis Ship
 * @since 4.0
 */
public class ExpressionCacheImpl implements ExpressionCache, ClassCacheInspector {

    private final ReentrantLock _lock = new ReentrantLock();

    private final Map _compiledExpressionCache = new HashMap();

    private final Map _expressionCache = new HashMap();

    private final boolean _cachingDisabled = Boolean.getBoolean("org.apache.tapestry.disable-caching");

    public ExpressionCacheImpl()
    {
        initializeService();
    }

    public void initializeService()
    {
        if (_cachingDisabled)
        {
            OgnlRuntime.setClassCacheInspector(this);
        }
    }

    public void reset()
    {
        try
        {
            _lock.lock();

            _compiledExpressionCache.clear();
            _expressionCache.clear();
        } finally
        {
            _lock.unlock();
        }
    }

    public boolean shouldCache(Class type)
    {
        if (!_cachingDisabled || type == null
            || AbstractComponent.class.isAssignableFrom(type))
            return false;

        return true;
    }

    public Object get(Object target, String expression)
    {
        try
        {
            _lock.lock();

            Map cached = (Map)_compiledExpressionCache.get(target.getClass());
            if (cached == null)
            {
                return _expressionCache.get(expression);
            } else
            {
                return cached.get(expression);
            }

        } finally
        {
            _lock.unlock();
        }
    }

    public void cache(Object target, String expression, Node node)
    {
        try
        {
            _lock.lock();

            if (node.getAccessor() != null)
            {
                Map cached = (Map)_compiledExpressionCache.get(target.getClass());

                if (cached == null)
                {
                    cached = new HashMap();
                    _compiledExpressionCache.put(target.getClass(), cached);
                }

                cached.put(expression, node);

                _expressionCache.remove(target.getClass());
            } else
            {
                _expressionCache.put(expression, node);
            }

        } finally
        {
            _lock.unlock();
        }
    }
}