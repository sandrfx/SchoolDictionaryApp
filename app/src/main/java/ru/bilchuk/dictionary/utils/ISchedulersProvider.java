package ru.bilchuk.dictionary.utils;

import io.reactivex.Scheduler;

public interface ISchedulersProvider {

    Scheduler io();
    Scheduler ui();
}
