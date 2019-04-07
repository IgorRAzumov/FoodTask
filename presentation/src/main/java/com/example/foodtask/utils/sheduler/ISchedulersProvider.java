package com.example.foodtask.utils.sheduler;

import io.reactivex.Scheduler;

public interface ISchedulersProvider {
    Scheduler mainThread();

    Scheduler io();

    Scheduler computation();
}
