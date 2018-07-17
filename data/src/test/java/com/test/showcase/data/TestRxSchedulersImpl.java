package com.test.showcase.data;

import com.test.showcase.data.rx.RxSchedulers;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

class TestRxSchedulersImpl implements RxSchedulers {

    @Override
    public Scheduler main(){
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler single(){
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler trampoline() {
        return Schedulers.trampoline();
    }
}
