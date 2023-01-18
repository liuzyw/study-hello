package com.study.timewheel.entity;

public class TimeTaskList {
    private TimeTaskEntry head;
    private TimeTaskEntry tail;

    private Integer size;

    public TimeTaskList() {
        head = new TimeTaskEntry();
        tail = new TimeTaskEntry();
        head.setNext(tail);
        tail.setPrefix(head);
        size = 0;
    }

    public TimeTaskEntry getHead() {
        return head;
    }

    public void setHead(TimeTaskEntry head) {
        this.head = head;
    }

    public TimeTaskEntry getTail() {
        return tail;
    }

    public void setTail(TimeTaskEntry tail) {
        this.tail = tail;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public synchronized void put(TimeTaskEntry value) {
        TimeTaskEntry temp = tail.getPrefix();
        temp.setNext(value);
        value.setPrefix(temp);
        value.setNext(tail);
        tail.setPrefix(value);
        size += 1;

    }

    public synchronized TimeTaskEntry get() {
        if (size == 0) {
            return null;
        }
        TimeTaskEntry temp = head.getNext();
        head.setNext(temp.getNext());
        temp.getNext().setPrefix(head);
        temp.setPrefix(null);
        temp.setNext(null);
        size -= 1;
        return temp;
    }

    public synchronized void printf() {
        if (size == 0) {
            System.out.println("");
        }

        TimeTaskEntry t = head.getNext();

        while (t.getNext() != null) {
            System.out.print(t.getData());
            System.out.print(", ");
            t = t.getNext();
        }

        System.out.println();
    }

    public synchronized void execute(int index) {
        if (size == 0) {
            System.out.println("");
            return;
        }
        System.out.println("当前 " + index + " 桶有数据: " + size);
        TimeTaskEntry t = head.getNext();

        while (t.getNext() != null) {
            System.out.print(t.getData());
            System.out.print(", ");
            t = t.getNext();
        }
        head.setNext(tail);
        tail.setPrefix(head);
        size = 0;
        System.out.println();

    }
}
