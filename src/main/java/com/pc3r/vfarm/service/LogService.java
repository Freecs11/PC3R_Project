package com.pc3r.vfarm.service;

import com.pc3r.vfarm.dao.LogDAO;
import com.pc3r.vfarm.entities.Log;

public class LogService extends GenericService<Log> {
    public LogService() {
        super(new LogDAO());
    }
}
