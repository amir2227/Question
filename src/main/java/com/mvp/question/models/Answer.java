package com.mvp.question.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 2048)
    private String content;
    @Column(length = 512)
    private String preview;
    @Column
    private Boolean is_accepted;
    @Column(length = 256)
    private String img;
    @Column
    private Boolean is_valid;
    @Column
    private Integer rating;
    @Column
    private Float gained_amount;
    @Column(length = 32)
    private String amount_unit;
    @Column(length = 1024)
    private String references;
    @ManyToOne
    @JoinColumn
    private Question question;
    @ManyToOne
    @JoinColumn
    private User answer_owner;
    @ManyToOne
    @JoinColumn
    private User answerer;
    public Answer() {
    }
    public Answer(String preview, Question question, User answer_owner) {
        this.preview = preview;
        this.question = question;
        this.answer_owner = answer_owner;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getPreview() {
        return preview;
    }
    public void setPreview(String preview) {
        this.preview = preview;
    }
    public Boolean getIs_accepted() {
        return is_accepted;
    }
    public void setIs_accepted(Boolean is_accepted) {
        this.is_accepted = is_accepted;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public Boolean getIs_valid() {
        return is_valid;
    }
    public void setIs_valid(Boolean is_valid) {
        this.is_valid = is_valid;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public Float getGained_amount() {
        return gained_amount;
    }
    public void setGained_amount(Float gained_amount) {
        this.gained_amount = gained_amount;
    }
    public String getAmount_unit() {
        return amount_unit;
    }
    public void setAmount_unit(String amount_unit) {
        this.amount_unit = amount_unit;
    }
    public String getReferences() {
        return references;
    }
    public void setReferences(String references) {
        this.references = references;
    }
    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
    public User getAnswer_owner() {
        return answer_owner;
    }
    public void setAnswer_owner(User answer_owner) {
        this.answer_owner = answer_owner;
    }

}
