package simpl.parser.ast;

import simpl.interpreter.Int;
import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        int oldPointer = s.p.get();
        Value v = e.eval(State.of(s.E, s.M, new Int(s.p.get()+1)));
        s.M.put(oldPointer, v);
        s.p.set(oldPointer+1);
        //System.out.println("put new "+oldPointer+" "+v.toString());
        return new RefValue(oldPointer);
        //return null;
    }
}
