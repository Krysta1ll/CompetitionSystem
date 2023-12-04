package csu.krystal.recommsys.service;

import csu.krystal.recommsys.entity.User;

public interface ITokenService {

    String createToken(String userName);
    int verify(String token);
    User getUserFromToken(String token);
}
