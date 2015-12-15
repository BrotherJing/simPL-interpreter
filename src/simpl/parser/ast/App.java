package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v = l.eval(s);
        FunValue fun = (FunValue)v;
        /*if(v instanceof RecValue){
            fun = (FunValue)((RecValue)v).e.eval(State.of(new Env(((RecValue) v).E,((RecValue) v).x,(RecValue) v), s.M, s.p));
            //System.out.println(fun.E.toString());
            //System.out.println(fun.toString());
        }else{
            fun = (FunValue)v;
        }*/

        // according to the rule E-App,
        // first evaluate the function,
        // then evaluate the parameter, using the original state.
        // then evaluate the expression in fun, using a new Env.
        
        Value param = r.eval(s);
        State newState = State.of(new Env(fun.E,fun.x,param),s.M,s.p);
        Value result = fun.e.eval(newState);
        return result;
        //return null;
    }
}
