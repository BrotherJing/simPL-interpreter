package simpl.typing;

import simpl.interpreter.PairValue;

public final class PairType extends Type {

    public Type t1, t2;

    public PairType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        if(t instanceof TypeVar){
            return t.unify(this);
        }if(t instanceof PairType){
            return t1.unify(((PairType) t).t1).compose(t2.unify(((PairType) t).t2));
        }
        throw new TypeMismatchError();
        //return null;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        t1=t1.replace(a,t);
        t2=t2.replace(a,t);
        return this;
        //return null;
    }

    public String toString() {
        return "(" + t1 + " * " + t2 + ")";
    }
}
