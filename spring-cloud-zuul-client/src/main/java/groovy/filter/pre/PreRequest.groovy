package groovy.filter.pre

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants

import javax.servlet.http.HttpServletRequest

/**
 *
 * Created on 2018-03-02
 * @author liuzhaoyuan
 *
 */
class PreRequest extends ZuulFilter {
    @Override
    String filterType() {
        return FilterConstants.PRE_TYPE
    }

    @Override
    int filterOrder() {
        return 1000
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
       println("pre filter")
        return null
    }


}