package org.joda.time;

import java.util.HashMap;

public class Pool {
    private static Pool myInstance;

    private GenericPool<Years> poolYears;
    private GenericPool<Months> poolMonths;
    private GenericPool<Weeks> poolWeeks;
    private GenericPool<Days> poolDays;
    private GenericPool<Hours> poolHours;
    private GenericPool<Minutes> poolMinutes;
    private GenericPool<Seconds> poolSeconds;


    private Pool() {
        this.poolYears = new GenericPool<Years>(Years.class);
        this.poolMonths = new GenericPool<Months>(Months.class);
        this.poolWeeks = new GenericPool<Weeks>(Weeks.class);
        this.poolDays = new GenericPool<Days>(Days.class);
        this.poolHours = new GenericPool<Hours>(Hours.class);
        this.poolMinutes = new GenericPool<Minutes>(Minutes.class);
        this.poolSeconds = new GenericPool<Seconds>(Seconds.class);
    }

    public static Pool getInstance() {

        if (myInstance == null) {
            myInstance = new Pool();
        }

        return myInstance;
    }

    public static Years retrieveYears(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.poolYears.retrieve(numeral);
    }

    public static Months retrieveMonths(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.poolMonths.retrieve(numeral);
    }

    public static Weeks retrieveWeeks(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.poolWeeks.retrieve(numeral);
    }

    public static Days retrieveDays(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.poolDays.retrieve(numeral);
    }

    public static Hours retrieveHours(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.poolHours.retrieve(numeral);
    }

    public static Minutes retrieveMinutes(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.poolMinutes.retrieve(numeral);
    }

    public static Seconds retrieveSeconds(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.poolSeconds.retrieve(numeral);
    }


    private class GenericPool<T>{
        private HashMap<Integer, T> pool;
        private final Class<T> clazz;


        private GenericPool(Class<T> clazz) {
            this.clazz = clazz;

            this.pool = new HashMap<Integer, T>();
        }

        public T retrieve(int numeral) {
            Object result = this.get(numeral);

            if (result == null) {
                result = createInstance(numeral);
                this.add(numeral, (T) result);
            }

            return (T) result;
        }

        private T createInstance(int numeral) {
            try {
                return clazz.getDeclaredConstructor( Integer.TYPE ).newInstance(numeral);

            } catch(Exception e) {
                return null;
            }
        }

        private void add(int numeral, T instance) {
            pool.put(new Integer(numeral), instance);
        }

        private Object get(int numeral){
            Object instance = pool.get(new Integer(numeral));

            return instance;
        }
    }
}
