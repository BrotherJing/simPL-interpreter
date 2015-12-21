package simpl.parser.ast;

import simpl.Logger;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Fn extends Expr {

    public Symbol x;
    public Expr e;

    public Fn(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(fn " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        //G[x:t]|-e:t2
        Logger.i("----------type check in Fn");
        Type a = new TypeVar(false);//x: a
        TypeResult tr2 = e.typecheck(TypeEnv.of(E, x, a));//u==>e:t
        Logger.i("e:"+tr2.t);

        Type b = new TypeVar(false);//f: a->b

        Substitution substitution = b.unify(tr2.t).compose(tr2.s);//b = t

        a = substitution.apply(a);
        b = substitution.apply(b);

        Logger.i("----------end check in Fn");
        return TypeResult.of(substitution,new ArrowType(a,b));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // according to E-Fn, we just need to return a FunValue with the original Env
        return new FunValue(s.E,x,e);
    }
}
