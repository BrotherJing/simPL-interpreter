package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Not extends UnaryExpr {

    public Not(Expr e) {
        super(e);
    }

    public String toString() {
        return "(not " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr = e.typecheck(E);

        Type t1 = tr.t;
        Substitution substitution = tr.s;
        t1 = substitution.apply(t1);

        substitution = t1.unify(Type.BOOL).compose(substitution);
        return TypeResult.of(substitution,Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue b = (BoolValue)(e.eval(s));
        return new BoolValue(!(b.b));
        //return null;
    }
}
