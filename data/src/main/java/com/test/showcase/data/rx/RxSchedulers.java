package com.test.showcase.data.rx;

import io.reactivex.Scheduler;

public interface RxSchedulers {
    Scheduler main();

    Scheduler single();

    Scheduler io();

    Scheduler computation();

    Scheduler trampoline();
}
