package com.test.showcase.data.rx;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulersImpl implements RxSchedulers {

    @Inject
    public RxSchedulersImpl() {}

    @Override
    public Scheduler main() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler single() {
        return Schedulers.single();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler trampoline() {
        return Schedulers.trampoline();
    }
}
