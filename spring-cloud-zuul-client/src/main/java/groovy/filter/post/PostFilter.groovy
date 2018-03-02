package groovy.filter.post

import com.netflix.zuul.ZuulFilter
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants

class PostFilter extends ZuulFilter {


    @Override
    String filterType() {
        return FilterConstants.POST_TYPE
    }

    @Override
    int filterOrder() {
        return 3
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        println("post filter")
        return null
    }
}