package com.example.reto3.service;

import com.example.reto3.entities.Score;
import com.example.reto3.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll() {
        return (List<Score>) scoreRepository.getAll();
    }

    public Optional<Score> getScore(int id) {
        return scoreRepository.getScore(id);
    }

    public Score save(Score score) {
        if (validarCampos(score)) {
            if (score.getIdScore() == null) {
                return scoreRepository.save(score);
            } else {
                Optional<Score> scoreEncontrado = getScore(score.getIdScore());
                if (scoreEncontrado.isEmpty()) {
                    return scoreRepository.save(score);
                } else {
                    return score;
                }
            }
        }
        return score;
    }

    public Score update(Score score) {
        if (validarCampos(score)) {
            if (score.getIdScore() != null) {
                Optional<Score> scoreEncontrado = getScore(score.getIdScore());
                if (!scoreEncontrado.isEmpty()) {
                    if (score.getMessageText() != null) {
                        scoreEncontrado.get().setMessageText(score.getMessageText());
                    }
                    if (score.getPoints() != null) {
                        scoreEncontrado.get().setPoints(score.getPoints());
                    }
                    return scoreRepository.save(scoreEncontrado.get());
                }
            }
            return score;
        }
        return score;
    }

    public boolean delete(int scoreId) {
        Boolean resultado = getScore(scoreId).map(elemento -> {
            scoreRepository.delete(elemento);
            return true;
        }).orElse(false);
        return resultado;
    }

    public boolean validarCampos(Score score) {
        return ((score.getPoints() >= 0 && score.getPoints() <= 5) && score.getMessageText().length() <= 250);
    }

}