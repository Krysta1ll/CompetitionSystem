package csu.krystal.recommsys.service;

public interface ITokenService {

    String createToken(String userName);
    int verify(String token);
    int getUidFromToken(String token);
}
