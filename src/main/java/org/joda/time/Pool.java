package org.joda.time;

import java.util.HashMap;

public class Pool {

    private static Pool myInstance;
    private HashMap<Integer, Years> years;
    private HashMap<Integer, Months> months;
    private HashMap<Integer, Weeks> weeks;
    private HashMap<Integer, Days> days;
    private HashMap<Integer, Hours> hours;
    private HashMap<Integer, Minutes> minutes;
    private HashMap<Integer, Seconds> seconds;


    private Pool() {
        this.years = new HashMap<Integer, Years>();
        this.months = new HashMap<Integer, Months>();
        this.weeks = new HashMap<Integer, Weeks>();
        this.days = new HashMap<Integer, Days>();
        this.hours = new HashMap<Integer, Hours>();
        this.minutes = new HashMap<Integer, Minutes>();
        this.seconds = new HashMap<Integer, Seconds>();
    }

    public static Pool getInstance() {

        if (myInstance == null) {
            myInstance = new Pool();
        }

        return myInstance;
    }

    public static Years retrieveYears(int numeral) {
        Pool pool = Pool.getInstance();

        Object result = pool.getYears(numeral);

        if (result == null) {
            result =  new Years(numeral);
            pool.addYears(numeral, (Years) result);
        }

        return (Years) result;
    }

    public static Months retrieveMonths(int numeral) {
        Pool pool = Pool.getInstance();

        Object result = pool.getMonths(numeral);

        if (result == null) {
            result =  new Months(numeral);
            pool.addMonths(numeral, (Months) result);
        }

        return (Months) result;
    }

    public static Weeks retrieveWeeks(int numeral) {
        Pool pool = Pool.getInstance();

        Object result = pool.getWeeks(numeral);

        if (result == null) {
            result =  new Weeks(numeral);
            pool.addWeeks(numeral, (Weeks) result);
        }

        return (Weeks) result;
    }


    public static Hours retrieveHours(int numeral) {
        Pool pool = Pool.getInstance();

        Object result = pool.getHours(numeral);

        if (result == null) {
            result =  new Hours(numeral);
            pool.addHours(numeral, (Hours) result);
        }

        return (Hours) result;
    }

    public static Days retrieveDays(int numeral) {
        Pool pool = Pool.getInstance();

        Object result = pool.getDays(numeral);

        if (result == null) {
            result =  new Days(numeral);
            pool.addDay(numeral, (Days) result);
        }

        return (Days) result;
    }

    public static Seconds retrieveSeconds(int numeral) {
        Pool pool = Pool.getInstance();

        Object result = pool.getSeconds(numeral);

        if (result == null) {
            result =  new Seconds(numeral);
            pool.addSeconds(numeral, (Seconds) result);
        }

        return (Seconds) result;
    }

    public static Minutes retrieveMinutes(int numeral) {

        Pool pool = Pool.getInstance();

        Object result = pool.getMinutes(numeral);

        if (result == null) {
            result =  new Minutes(numeral);
            pool.addMinutes(numeral, (Minutes) result);
        }

        return (Minutes) result;
    }

    private void addYears(int numeral, Years year) {
        years.put(new Integer(numeral), year);
    }

    private void addMonths(int numeral, Months month) {
        months.put(new Integer(numeral), month);
    }
    private void addWeeks(int numeral, Weeks week) {
        weeks.put(new Integer(numeral), week);
    }
    
    private void addDay(int numeral, Days day) {
        days.put(new Integer(numeral), day);
    }

    private void addHours(int numeral, Hours hour) {
        hours.put(new Integer(numeral), hour);
    }

    private void addSeconds(int numeral, Seconds second) {
        seconds.put(new Integer(numeral), second);
    }


    private void addMinutes(int numeral, Minutes minute) {
        minutes.put(new Integer(numeral), minute);
    }

    private Object getYears(int numeral){
        Object instance = years.get(new Integer(numeral));

        return instance;
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
