package simpl.parser.ast;

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
        // TODO
        //TypeResult tr2 = e.typecheck(TypeEnv.of(E, x, t))
        //G[x:t]|-e:t2
        System.out.println("----------type check in Fn");
        TypeVar a = new TypeVar(false);//x: a
        TypeResult tr2 = e.typecheck(TypeEnv.of(E, x, a));//u==>e:t
        System.out.println("e:"+tr2.t);

        TypeVar b = new TypeVar(false);//f: a->b

        Substitution substitution = b.unify(tr2.t).compose(tr2.s);//b = t
        System.out.println(substitution);
        Type new_a = substitution.apply(a);
        Type new_b = substitution.apply(b);
        //substitution.apply(tr2.t);

        System.out.println("----------end check in Fn");
        return TypeResult.of(substitution,new ArrowType(new_a,new_b));
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        // according to E-Fn, we just need to return a FunValue with the original Env
        return new FunValue(s.E,x,e);
        //return null;
    }
}
