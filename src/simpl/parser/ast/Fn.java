package simpl.parser.ast;

import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

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
        System.out.println("type check in Fn");
        TypeVar a = new TypeVar(false);//x:a
        TypeResult tr2 = e.typecheck(TypeEnv.of(E, x, a));//u==>e:t
        System.out.println(tr2.t);
        System.out.println(tr2.s);
        Type newType = tr2.s.apply(a);
        System.out.println("end check in Fn");
        return TypeResult.of(tr2.s,new ArrowType(newType,tr2.t));
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
