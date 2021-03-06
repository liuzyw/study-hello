package com.study.spring.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.study.spring.entity.Stock;
import com.study.spring.service.StockService;
import com.study.spring.vo.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018-06-23
 *
 * @author liuzhaoyuan
 */
@Controller
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StockService stockService;

    @Autowired
    private RedisTemplate redisTemplate;

    // 限流
    private RateLimiter rateLimiter = RateLimiter.create(100);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private static final int partition = 10;


    @RequestMapping(value = "/goStock", method = RequestMethod.GET)
    public String toPage() {
        return "stock/stock";
    }


    @RequestMapping(value = "/addStock", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> addProduct(@RequestBody Stock stock) {

        LOGGER.info("save stock: " + stock);
        stockService.saveStock(stock);

        int count = stock.getCount();

        // 分段
        List<Integer> seg = new ArrayList<>();
        for (int i = 0; i < partition; i++) {
            seg.add(0);
        }
        for (int i = 0; i < count; i++) {
            int index = i % partition;
            seg.set(index, seg.get(index) + 1);
        }

        LOGGER.info("segmentation product: " + seg);

        redisTemplate.opsForValue().set(stock.getProductId().toString(), seg);
        for (int i = 0; i < partition; i++) {
            redisTemplate.opsForValue().set(stock.getProductId() + "_" + i, seg.get(i));
        }
        LOGGER.info("save stock success " + stock);

        return new Result<>(true, 200);
    }


    /**
     * ajax 返回string 对象，前端不能正常解析，所以需要包装一下 大坑
     *
     * @param stock
     *
     * @return
     */
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> buy(@RequestBody Stock stock) {

        LOGGER.info("buy product: " + stock);

        if (stock.getProductId() == null) {
            return new Result<>("productId is empty", 201);
        }

        // 性能不行
//        rateLimiter.acquire(1);
//        if (!rateLimiter.tryAcquire(200, TimeUnit.MILLISECONDS)) {
//            LOGGER.warn("too many request ... ");
//            return new Result<>("request time out", 201);
//        }

        long timeMillis = System.currentTimeMillis();

        final int index = (int) timeMillis % partition;

        LOGGER.info("buy product seg index: " + index);


        Integer count = (Integer) redisTemplate.opsForValue().get(stock.getProductId().toString() + "_" + index);

        if (count == null || count < 1) {
            return new Result<>("stock is not enough", 201);
        }

        Long result = redisTemplate.opsForValue().increment(stock.getProductId().toString() + "_" + index, Long.valueOf(stock.getCount() * -1));
        LOGGER.info("redis result count:" + result);
        if (result > 0) {
            return new Result<>("success", 200);
        } else if (result == 0) {

            threadPoolTaskExecutor.execute(() -> {
                LOGGER.info("update product: " + stock);
                List<Integer> seg = (List<Integer>) redisTemplate.opsForValue().get(stock.getProductId().toString());
                int reduce = seg.get(index);

//                Stock originStock = stockService.getStockByProductId(stock.getProductId());

//                if (originStock.getCount() > 0) {
//                    stockService.updateStockByProductId(stock.getProductId(), originStock.getCount(), 0);
                    stockService.updateReduceStockByProductId(stock.getProductId(), reduce);
//                }
            });
            return new Result<>("success", 200);
        } else {
            return new Result<>("concurrency fail", 201);
        }
    }


}
