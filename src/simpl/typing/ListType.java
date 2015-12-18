package simpl.typing;

public final class ListType extends Type {

    public Type t;

    public ListType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        if(t instanceof ListType){
            return this.t.unify(((ListType) t).t);
        }else if(t instanceof TypeVar){
            return t.unify(this);
        }
        throw new TypeError("not a list");
        //return null;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return t.contains(tv);
        //return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        this.t = this.t.replace(a,t);
        return this;
        //return null;
    }

    public String toString() {
        return t + " list";
    }
}
