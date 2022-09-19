package com.mvp.question.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.mvp.question.models.enums.CoinType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;

@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class)
})
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 256, nullable = false)
    private String title;
    @Column(length = 2048, nullable = false)
    private String content;
    @Column(length = 256, nullable = true)
    private String img;
    @Column(nullable = false)
    private Float reward_amount;
    @Enumerated(EnumType.STRING)
    @Column(length = 32, nullable = false)
    private CoinType reward_unit;
    @Column(nullable = true)
    private Integer expire_date;
    @Column(nullable = false)
    private Boolean is_active;
    @Column(nullable = false)
    private Boolean hide_answers;
    @Column(nullable = false)
    private Boolean is_valide;
    @Type(type = "string-array")
    @Column(nullable = false, columnDefinition = "text[]")
    private String[] tags;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    // add report col
    public Question() {
    }

    public Question(String title, String content, Float reward_amount, CoinType reward_unit,
            Integer expire_date, Boolean hide_answers, String[] tags, User owner) {
        this.title = title;
        this.content = content;
        this.reward_amount = reward_amount;
        this.reward_unit = reward_unit;
        this.expire_date = expire_date;
        this.hide_answers = hide_answers;
        this.tags = tags;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Float getReward_amount() {
        return reward_amount;
    }

    public void setReward_amount(Float reward_amount) {
        this.reward_amount = reward_amount;
    }

    public CoinType getReward_unit() {
        return reward_unit;
    }

    public void setReward_unit(CoinType reward_unit) {
        this.reward_unit = reward_unit;
    }

    public Integer getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(Integer expire_date) {
        this.expire_date = expire_date;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Boolean getHide_answers() {
        return hide_answers;
    }

    public void setHide_answers(Boolean hide_answers) {
        this.hide_answers = hide_answers;
    }

    public Boolean getIs_valide() {
        return is_valide;
    }

    public void setIs_valide(Boolean is_valide) {
        this.is_valide = is_valide;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
