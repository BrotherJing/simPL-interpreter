package simpl.parser.ast;

import simpl.Logger;
import simpl.interpreter.BoolValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        Logger.i("----------type check in Cond");
        TypeResult tr1 = e1.typecheck(E);
        Logger.i("e1:" + tr1.t);
        TypeResult tr2 = e2.typecheck(E);
        Logger.i("e2:" + tr2.t);
        TypeResult tr3 = e3.typecheck(E);
        Logger.i("e3:" + tr3.t);

        TypeVar resultType = new TypeVar(false);

        Type t1 = tr1.t;
        Type t2 = tr2.t;
        Type t3 = tr3.t;

        Substitution substitution = tr3.s.compose(tr2.s).compose(tr1.s);

        t1 = substitution.apply(t1);
        t2 = substitution.apply(t2);
        t3 = substitution.apply(t3);

        substitution = t1.unify(Type.BOOL).compose(substitution);

        t2 = substitution.apply(t2);
        t3 = substitution.apply(t3);

        substitution = t2.unify(resultType).compose(substitution);

        t3 = substitution.apply(t3);

        substitution = t3.unify(resultType).compose(substitution);


        TypeResult result = TypeResult.of(substitution,substitution.apply(resultType));

        Logger.i("----------end check in Cond");
        return result;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v1 = e1.eval(s);
        if(!(v1 instanceof BoolValue))throw new RuntimeError("not a bool value");
        if(((BoolValue)v1).b){
            return e2.eval(s);
        }else{
            return e3.eval(s);
        }
    }
}
