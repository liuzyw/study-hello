package com.study.handler.condition;

import com.alibaba.fastjson.JSON;
import com.study.constants.Constant;
import com.study.handler.StateHandlerService;
import com.study.model.ConditionCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class PayAmountConditionHandler implements ConditionHandler {

    @Autowired
    private StateHandlerService stateHandlerService;

    public PayAmountConditionHandler() {
        stateHandlerService.registerConditionHandler(this);
    }

    public class PayAmount {

        private Integer payAmount;
        private String op;

        public Integer getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(Integer payAmount) {
            this.payAmount = payAmount;
        }

        public String getOp() {
            return op;
        }

        public void setOp(String op) {
            this.op = op;
        }

        public boolean compare(String am) {
            switch (this.op) {
                case "<":
                    return this.payAmount.intValue() < Integer.valueOf(am).intValue();

                case ">":
                    return this.payAmount.intValue() > Integer.valueOf(am).intValue();
                default:
                    return this.payAmount.intValue() == Integer.valueOf(am).intValue();
            }
        }
    }


    @Override
    public String getConditionName() {
        return Constant.PAY_AMOUNT;
    }

    @Override
    public boolean executeCondition(ConditionCombination condition, Map<String, String> params) {

        PayAmount payAmount = JSON.parseObject(condition.getConditionExpress(), PayAmount.class);
        return payAmount.compare(params.get(Constant.PAY_AMOUNT));
    }
}
