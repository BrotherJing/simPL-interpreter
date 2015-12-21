package simpl.parser.ast;

import simpl.Logger;
import simpl.interpreter.Int;
import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        Logger.i("----------type check in Ref");
        TypeResult tr = e.typecheck(E);
        Logger.i("e:" + tr.t);

        Type t = tr.t;
        Substitution substitution = tr.s;

        t = substitution.apply(t);

        TypeResult result = TypeResult.of(substitution,new RefType(t));

        Logger.i("----------end check in Ref");
        return result;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        int oldPointer = s.p.get();
        s.p.set(oldPointer+1);
        Value v = e.eval(s);
        s.M.put(oldPointer, v);
        return new RefValue(oldPointer);
    }
}
