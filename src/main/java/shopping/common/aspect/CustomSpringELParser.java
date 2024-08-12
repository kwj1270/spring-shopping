package shopping.common.aspect;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringELParser {
    private CustomSpringELParser() {
    }

    public Object getDynamicValue(String[] parameterNames, Object[] args, String key) {
        final ExpressionParser parser = new SpelExpressionParser();
        final StandardEvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        return parser.parseExpression(key).getValue(context, Object.class);
    }
}