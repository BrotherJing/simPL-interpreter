package simpl.interpreter.lib;

import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class hd extends FunValue {

    public hd() {
        super(Env.empty,Symbol.symbol("x"),new Expr(){

            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                ConsValue cv = (ConsValue)(s.E.get(Symbol.symbol("x")));
                return cv.v1;
            }
            
        });
    }
}
