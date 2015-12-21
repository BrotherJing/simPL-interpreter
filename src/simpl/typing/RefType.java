package simpl.typing;

public final class RefType extends Type {

    public Type t;

    public RefType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        if(t instanceof TypeVar){
            return t.unify(this);
        }else if(t instanceof RefType){
            return this.t.unify(((RefType)t).t);
        }
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        return t.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        this.t = this.t.replace(a,t);
        return this;
    }

    public String toString() {
        return t + " ref";
    }
}
