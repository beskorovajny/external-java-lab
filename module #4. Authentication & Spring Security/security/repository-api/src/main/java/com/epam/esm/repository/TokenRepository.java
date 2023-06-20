package com.epam.esm.repository;

import com.epam.esm.core.jwt.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository {

    boolean isExists(Token token);

    Token save(Token token);

    Optional<Token> findByToken(String token);

    List<Token> findAllValidByUser(Long userID);

    Token deleteByID(Long id);

}
