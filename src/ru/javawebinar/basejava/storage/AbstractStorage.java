package ru.javawebinar.basejava.storage;

import java.util.Arrays;

public abstract class AbstractStorage implements Storage {


    protected abstract int getIndex(String uuid);


}
