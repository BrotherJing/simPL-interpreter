package simpl.typing;

import simpl.parser.Symbol;

public class TypeVar extends Type {

    private static int tvcnt = 0;

    private boolean equalityType;

    //originally was private
    public Symbol name;

    //modify
    private int id;

    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        id = tvcnt;
        name = Symbol.symbol("tv" + ++tvcnt);

        //System.out.println("new TypeVar "+(char)('a'+id));
    }

    @Override
    public boolean isEqualityType() {
        return equalityType;
    }

    @Override
    public Substitution unify(Type t) throws TypeCircularityError {
        if(t.contains(this)){
            throw new TypeCircularityError();
        }
        return Substitution.of(this,t);
    }

    public String toString() {
        //return (char)('a'+id)+"";
        return name.toString();
    }

    @Override
    public boolean contains(TypeVar tv) {
        return name.equals(tv.name);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        if(name.equals(a.name)) return t;
        else return this;
    }
}
