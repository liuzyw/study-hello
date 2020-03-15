package com.study.spring.state.model;

import com.study.spring.state.enums.ConstraintEnum;
import com.study.spring.state.enums.RuleTypeEnum;
import com.study.util.tool.RandomUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.jexl2.JexlEngine;

/**
 * Created on 2020-03-15
 *
 * @author liuzhaoyuan
 */
public final class StateHelper {

    private static final JexlEngine JEXL_ENGINE = new JexlEngine();

    public static StateScene generate(StateMachineConfigDefinition stateMachineConfigDefinition) {

        StateScene stateScene = new StateScene();

        stateScene.setBizCategory(stateMachineConfigDefinition.getBizCategory());
        stateScene.setStateNode(generateStateNode(stateMachineConfigDefinition.getStateNodeDefinition()));

        return stateScene;

    }


    public static StateNode generateStateNode(StateNodeDefinition stateNodeDefinition) {

        StateNode stateNode = new StateNode();

        stateNode.setStart(stateNodeDefinition.isStart());
        stateNode.setStartCode(stateNodeDefinition.getStartCode());
        stateNode.setActionConditions(generateActionCondition(stateNodeDefinition.getActionConditionDefinitions()));
        stateNode.setStateConditions(generateStateCondition(stateNodeDefinition.getStateConditionDefinitions()));

        return stateNode;

    }


    public static List<StateCondition> generateStateCondition(List<StateConditionDefinition> stateConditionDefinitions) {

        if (stateConditionDefinitions != null && stateConditionDefinitions.size() > 0) {
            List<StateCondition> result = new ArrayList<>();
            for (StateConditionDefinition definition : stateConditionDefinitions) {
                StateCondition stateCondition = new StateCondition();

                stateCondition.setCondition(generateCondition(definition.getConditionDefinition()));
                stateCondition.setStateNode(generateStateNode(definition.getStateNodeDefinition()));

                result.add(stateCondition);
            }
            return result;

        }

        return null;
    }


    public static Condition generateCondition(ConditionDefinition definition) {
        Condition condition = new Condition();
        condition.setJexlExpression(JEXL_ENGINE.createExpression(definition.getJexlExpression()));
        condition.setRules(generateRules(definition.getRuleDefinitions()));
        return condition;

    }


    public static List<Rule> generateRules(List<RuleDefinition> ruleDefinitions) {
        if (ruleDefinitions == null || ruleDefinitions.size() == 0) {
            return null;
        }

        List<Rule> rules = new ArrayList<>();

        for (RuleDefinition definition : ruleDefinitions) {
            Rule rule = new Rule();
            rule.setId(definition.getId());
            rule.setInput(definition.getInput());
            rule.setConstraintValue(definition.getConstraintValue());
            rule.setRuleTypeEnum(RuleTypeEnum.getRuleType(definition.getType()));
            rule.setConstraintEnum(ConstraintEnum.getConstraintEnumBySymbol(definition.getConstraint()));

            rules.add(rule);
        }

        return rules;

    }


    public static List<ActionCondition> generateActionCondition(List<ActionConditionDefinition> definitions) {

        if (definitions != null && definitions.size() > 0) {
            List<ActionCondition> result = new ArrayList<>();

            for (ActionConditionDefinition definition : definitions) {
                Collections.sort(definition.getActionItemDefinitions());

                ActionCondition actionCondition = new ActionCondition();

                actionCondition.setCondition(generateCondition(definition.getConditionDefinition()));

                actionCondition.setActionItemDefinitions(definition.getActionItemDefinitions());

                result.add(actionCondition);
            }
            return result;

        }

        return null;
    }


    public static String generateOrderId(){
        StringBuilder sb = new StringBuilder();
        sb.append("SO");
        sb.append(DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        sb.append(RandomUtil.randomNumbers(6));

        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        System.out.println(generateOrderId());
        System.out.println(generateOrderId());
        System.out.println(generateOrderId());


    }

}
