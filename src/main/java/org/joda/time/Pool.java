package org.joda.time;

import java.util.HashMap;

public class Pool {
    private static Pool myInstance;

    private GenericPool<Years> years;
    private GenericPool<Months> months;
    private GenericPool<Weeks> weeks;
    private GenericPool<Days> days;
    private GenericPool<Hours> hours;
    private GenericPool<Minutes> minutes;
    private GenericPool<Seconds> seconds;


    private Pool() {
        this.years = new GenericPool<Years>(Years.class);
        this.months = new GenericPool<Months>(Months.class);
        this.weeks = new GenericPool<Weeks>(Weeks.class);
        this.days = new GenericPool<Days>(Days.class);
        this.hours = new GenericPool<Hours>(Hours.class);
        this.minutes = new GenericPool<Minutes>(Minutes.class);
        this.seconds = new GenericPool<Seconds>(Seconds.class);
    }

    public static Pool getInstance() {

        if (myInstance == null) {
            myInstance = new Pool();
        }

        return myInstance;
    }

    public static Years retrieveYears(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.years.retrieve(numeral);
    }

    public static Months retrieveMonths(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.months.retrieve(numeral);
    }

    public static Weeks retrieveWeeks(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.weeks.retrieve(numeral);
    }

    public static Days retrieveDays(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.days.retrieve(numeral);
    }

    public static Hours retrieveHours(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.hours.retrieve(numeral);
    }

    public static Minutes retrieveMinutes(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.minutes.retrieve(numeral);
    }

    public static Seconds retrieveSeconds(int numeral) {
        Pool pool = Pool.getInstance();

        return pool.seconds.retrieve(numeral);
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
