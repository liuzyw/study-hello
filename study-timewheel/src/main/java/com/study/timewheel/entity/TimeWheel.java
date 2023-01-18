package com.study.timewheel.entity;


public class TimeWheel {

    private int size;

    private Bucket[] arr;

    private int interval;  // 时间跨度 秒

    private int index;

    private int maxInterval;

    public TimeWheel() {
        this(10, 1);
    }

    public TimeWheel(Integer size) {
        this(size, 1);
    }

    public TimeWheel(int size, int interval) {
        this.maxInterval = size * interval;
        this.index = 0;
        this.size = size;
        this.interval = interval;
        this.arr = new Bucket[size];
        for (int i = 0; i < size; i++) {
            this.arr[i] = new Bucket();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                long count = 1;
                for (; ; ) {
                    System.out.println("第：" + count + " 秒");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            arr[index].getList().execute(index);

                        }
                    }).start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    count += 1;
                    index = (index + 1) % size;
                }
            }
        }).start();

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Bucket[] getArr() {
        return this.arr;
    }

    public void setArr(Bucket[] arr) {
        this.arr = arr;
    }

    public int getInterval() {
        return interval;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }


    public synchronized void addTaskEntry(TimeTaskEntry timeTaskEntry) {

        if (timeTaskEntry.getDelayTime() > maxInterval) {
            System.out.println("时间太大，不支持");
            return;
        }

        int step = timeTaskEntry.getDelayTime() / interval;


        int tIndex = (index + step) % size;
        arr[tIndex].getList().put(timeTaskEntry);
    }
}
