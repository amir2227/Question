package com.mvp.question.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mvp.question.exceptions.BadRequestException;
import com.mvp.question.models.Question;
import com.mvp.question.models.User;
import com.mvp.question.models.Wallet;
import com.mvp.question.models.enums.CoinType;
import com.mvp.question.payload.request.QuestionRequest;
import com.mvp.question.repository.QuestionRepo;

public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private UserService userService;

    public Question create(QuestionRequest request, Long user_id) {
        User owner = userService.get(user_id);
        // check user balance and validate amount
        List<Wallet> wallets = new ArrayList<>();
        float total_amount = 0;
        owner.getWallets().forEach(x -> {
            if (x.getCoin_type().equals(request.getReward_unit())) {
                wallets.add(x);
            }
        });
        if (wallets.size() < 1)
            throw new BadRequestException(
                    String.format("you have not %s in your wallet", request.getReward_unit()));

        for (Wallet w : wallets) {
            total_amount += w.getAmount();
        }
        if (total_amount < request.getReward_amount())
            throw new BadRequestException(
                    String.format("you have not enough %s in your wallet", request.getReward_unit()));
        Question question = new Question(
                request.getTitle(), request.getContent(),
                request.getReward_amount(), request.getReward_unit(),
                request.getExpire_date(), request.getHide_answers(),
                request.getTags(), owner);
        List<Wallet> nWallets = this.subtractAmount(wallets, request.getReward_amount());
        // update wallets 
        return questionRepo.save(question);
    }

    public List<Wallet> subtractAmount(List<Wallet> wallets, Float amount) {
        for (Wallet w : wallets) {
            if (w.getAmount() < amount) {
                amount = amount - w.getAmount();
                wallets.remove(w);
            } else {
                if (amount == 0f)
                    break;
                w.setAmount(w.getAmount() - amount);
                amount = 0f;
            }
        }
        return wallets;
    }

    // public Question edite() {
    // }

    // public Question get() {
    // }

    // public List<Question> search() {
    // }

    // public String delete() {
    // }
}
