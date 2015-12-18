package simpl.typing;

import simpl.parser.Symbol;

public abstract class Substitution {

    public abstract Type apply(Type t);

    private static final class Identity extends Substitution {
        public Type apply(Type t) {
            /*System.out.print("apply identity on ");
            if(t==null)
                System.out.println("what the fuck?");
            else
                System.out.println(t);*/
            return t;
        }

        @Override
        public String toString() {
            return "";
        }
    }

    private static final class Replace extends Substitution {
        private TypeVar a;
        private Type t;

        public Replace(TypeVar a, Type t) {
            this.a = a;
            this.t = t;
        }

        public Type apply(Type b) {//apply on type scheme b? replace a with t?
            System.out.print("apply replace "+a+" with "+t+" on ");
            if(b==null)
                System.out.println("what the fuck?");
            else
                System.out.println(b);
            return b.replace(a, t);
        }

        @Override
        public String toString() {
            return "replace "+a+" with "+t+'\n';
        }
    }

    private static final class Compose extends Substitution {
        private Substitution f, g;

        //public List<Replace> replaceList;

        public Compose(Substitution f, Substitution g) {
            this.f = f;
            this.g = g;
            /*replaceList = new ArrayList<>();
            if(f instanceof Replace)
                replaceList.add((Replace)f);
            if(g instanceof Replace)
                replaceList.add((Replace)g);*/
        }

        public Type apply(Type t) {
            //System.out.println("apply on "+t.toString());
            return f.apply(g.apply(t));
            /*Type result = t;
            for(int i=0;i<replaceList.size();++i){
                Replace replace = replaceList.get(i);
                result = replace.apply(result);
                *//*for(int j=0;j<replaceList.size();++j){

                }*//*
            }
            return result;*/
        }

        @Override
        public String toString() {
            return f.toString()+g.toString();
            /*StringBuilder stringBuilder = new StringBuilder();
            for(Replace replace:replaceList){
                stringBuilder.append(replace.toString()+'\n');
            }
            return stringBuilder.toString();*/
        }

        /*@Override
        public Substitution compose(Substitution inner) {
            if(inner instanceof Replace){
                replaceList.add((Replace)inner);
            }else if(inner instanceof Compose){
                replaceList.addAll(((Compose) inner).replaceList);
            }
            return this;
            //return super.compose(inner);
        }*/
    }

    public static final Substitution IDENTITY = new Identity();

    public static Substitution of(TypeVar a, Type t) {//Substitution.of(a,t), replace a with t
        return new Replace(a, t);
    }

    public Substitution compose(Substitution inner) {//xx.compose(inner), xx.apply(inner.apply(t))
        return new Compose(this, inner);
    }

    public TypeEnv compose(final TypeEnv E) {
        return new TypeEnv() {
            public Type get(Symbol x) {
                return apply(E.get(x));
            }
        };
    }

    @Override
    public String toString() {
        return "fuck?";
    }
}
