package csu.krystal.recommsys.service;

import csu.krystal.recommsys.entity.Model;

import java.util.List;

public interface IRedisService {
    List<Model> getAllModelInMemory();
}
