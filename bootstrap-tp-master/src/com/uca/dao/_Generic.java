package com.uca.dao;

import java.sql.Connection;

public abstract class _Generic<T> {

    public Connection connect = _Connector.getInstance();

}
